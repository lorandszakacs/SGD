package com.lorandszakacs.sg.model.impl

import com.lorandszakacs.sg.model._
import com.lorandszakacs.util.effects._
import com.lorandszakacs.util.mongodb._

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 14 Jul 2017
  *
  */
private[impl] class RepoHFIndex(override protected val db: Database)(
  implicit
  override val dbIOScheduler: DBIOScheduler,
  override val futureLift: FutureLift[IO],
) extends IndexSingleDocRepo[HFIndex] with SGRepoBSON {

  override protected def objectHandler: BSONDocumentHandler[HFIndex] = BSONMacros.handler[HFIndex]

  override protected def uniqueDocumentId: String = "hf_index"

  override protected def defaultEntity: HFIndex = HFIndex(
    names           = Nil,
    needsReindexing = Nil,
    number          = 0,
  )

  private def sanitize(i: HFIndex): HFIndex = {
    val temp = i.names.distinct.sorted
    i.copy(
      names           = temp,
      needsReindexing = i.needsReindexing.distinct.sorted,
      number          = temp.size,
    )
  }

  private def sanitize(names: List[Name]): HFIndex = {
    val temp = names.distinct.sorted
    HFIndex(
      names           = temp,
      needsReindexing = temp,
      number          = temp.length,
    )
  }

  override def create(sg: HFIndex): IO[Unit] = {
    super.create(sanitize(sg))
  }

  override def createOrUpdate(sg: HFIndex): IO[Unit] = {
    super.createOrUpdate(sanitize(sg))
  }

  def rewriteIndex(names: List[Name]): IO[Unit] = {
    this.createOrUpdate(sanitize(names))
  }

}
