package com.lorandszakacs.sg.model.impl

import com.lorandszakacs.sg.model._
import com.typesafe.scalalogging.StrictLogging
import org.joda.time.LocalDate
import com.lorandszakacs.util.future._
import com.lorandszakacs.util.list._
import com.lorandszakacs.util.mongodb.Database
import com.lorandszakacs.util.time._

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 04 Jul 2016
  *
  */
private[model] class SGAndHFRepositoryImpl(
  val db:          Database
)(implicit val ec: ExecutionContext)
    extends SGAndHFRepository with StrictLogging {

  private val sgsRepo = new RepoSGs(db)
  private val sgiRepo = new RepoSGIndex(db)
  private val hfsRepo = new RepoHFs(db)
  private val hfiRepo = new RepoHFIndex(db)
  private val lpmRepo = new RepoLastProcessedMarker(db)

  override def reindexSGs(names: List[Name]): IO[Unit] = {
    sgiRepo.rewriteIndex(names)
  }

  override def reindexHFs(names: List[Name]): IO[Unit] = {
    hfiRepo.rewriteIndex(names)
  }

  override def markAsIndexed(newHFs: List[HF], newSGs: List[SG]): IO[Unit] = {
    markAsIndexedForNames(
      newHFs = newHFs.map(_.name),
      newSGs = newSGs.map(_.name)
    )
  }

  override def markAsIndexedForNames(newHFs: List[Name], newSGs: List[Name]): IO[Unit] = {

    /**
      *
      * @return
      * HFs that became SGS
      */
    def updateHF(newHFs: List[Name]): IO[List[Name]] = {
      if (newHFs.isEmpty) {
        IO.pure(Nil)
      }
      else {
        for {
          ikdHFs <- hfiRepo.get
          hfsThatBecameSGS: List[Name] = ikdHFs.names.filter { ohn =>
            newSGs.contains(ohn.stripUnderscore)
          }
          //we remove all the HFs that became SGs from the index
          newHFIndex = ikdHFs.copy(
            names = (ikdHFs.names ++ newHFs).filterNot(n => hfsThatBecameSGS.contains(n.stripUnderscore)),
            needsReindexing =
              ikdHFs.needsReindexing.diff(newHFs).filterNot(n => hfsThatBecameSGS.contains(n.stripUnderscore))
          )
          _ <- hfiRepo.createOrUpdate(newHFIndex)
          _ <- IO.traverse(hfsThatBecameSGS) { hfName =>
                hfsRepo.remove(hfName)
              }
        } yield hfsThatBecameSGS
      }
    }

    def updateSGs(newSGs: List[Name]): IO[Unit] = {
      if (newSGs.isEmpty) {
        IO.unit
      }
      else {
        for {
          oldSGs <- sgiRepo.get
          newSGIndex = oldSGs.copy(
            names           = oldSGs.names ++ newSGs,
            needsReindexing = oldSGs.needsReindexing.diff(newSGs)
          )
          _ <- sgiRepo.createOrUpdate(newSGIndex)
        } yield ()
      }

    }

    for {
      hfsThatBecameSGs <- updateHF(newHFs)
      _                <- updateSGs(newSGs)
    } yield {
      logger.info(s"new SGs: ${newSGs.stringify}")
      logger.info(s"new HFs: ${newHFs.stringify}")
      logger.info(s"HFs that became SGs: ${hfsThatBecameSGs.stringify}")
    }
  }

  override def createOrUpdateLastProcessed(l: LastProcessedMarker): IO[Unit] = {
    lpmRepo.createOrUpdate(l)
  }

  override def lastProcessedIndex: IO[Option[LastProcessedMarker]] = {
    lpmRepo.find
  }

  def completeIndex: IO[CompleteIndex] = {
    for {
      hfIndex <- hfiRepo.get
      sgIndex <- sgiRepo.get
    } yield {
      val normalizedNames      = (hfIndex.names ++ sgIndex.names).distinct.sorted
      val normalizedReindexing = (hfIndex.needsReindexing ++ sgIndex.needsReindexing).distinct.sorted
      CompleteIndex(
        names           = normalizedNames,
        needsReindexing = normalizedReindexing,
        number          = normalizedNames.length
      )
    }
  }

  override def createOrUpdateSGs(sgs: List[SG]): IO[Unit] = {
    for {
      _ <- sgsRepo.createOrUpdate(sgs)
      _ <- this.markAsIndexed(newHFs = Nil, newSGs = sgs)
    } yield ()
  }

  override def createOrUpdateHFs(hfs: List[HF]): IO[Unit] = {
    for {
      _ <- hfsRepo.createOrUpdate(hfs)
      _ <- this.markAsIndexed(newHFs = hfs, newSGs = Nil)
    } yield ()
  }

  private def groupMsBetweenDays(start: LocalDate, end: LocalDate, ms: List[M]): List[(LocalDate, List[M])] = {
    val days = TimeUtil.daysBetween(start, end)
    for {
      day <- days
      msForDay = ms.filter(_.photoSets.exists(_.date == day))
    } yield (day, msForDay)
  }

  override def aggregateBetweenDays(
    start: LocalDate,
    end:   LocalDate,
    ms:    List[M]
  ): IO[List[(LocalDate, List[M])]] = {
    for {
      sgs <- sgsRepo.findBetweenDays(start, end)
      hfs <- hfsRepo.findBetweenDays(start, end)
      allFromDB = sgs ++ hfs: List[M]
      all       = allFromDB.addOrReplace(ms)

      result = groupMsBetweenDays(start, end, all)
    } yield result
  }

  override def find(name: Name): IO[Option[M]] = {
    for {
      sg <- sgsRepo.find(name)
      hf <- hfsRepo.find(name)
    } yield if (sg.isDefined) sg else hf
  }

  override def find(names: Seq[Name]): IO[List[M]] = {
    for {
      sgs <- sgsRepo.findManyById(names)
      hfs <- hfsRepo.findManyById(names)
    } yield (sgs ++ hfs).sortBy(_.name)
  }

  override def findAll: IO[List[M]] = {
    for {
      sgs <- sgsRepo.findAll
      hfs <- hfsRepo.findAll
    } yield (sgs ++ hfs).sortBy(_.name)
  }

}