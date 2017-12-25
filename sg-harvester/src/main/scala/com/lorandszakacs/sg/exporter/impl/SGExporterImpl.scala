package com.lorandszakacs.sg.exporter.impl

import com.github.nscala_time.time.Imports._
import com.lorandszakacs.sg.Favorites
import com.lorandszakacs.sg.exporter.html.{HTMLGenerator, HtmlSettings, MRootIndex}
import com.lorandszakacs.sg.exporter.indexwriter.{HTMLIndexWriter, WriterSettings}
import com.lorandszakacs.sg.exporter.{ExporterSettings, ModelNotFoundException, SGExporter}
import com.lorandszakacs.sg.model._
import com.lorandszakacs.util.future._
import com.typesafe.scalalogging.StrictLogging

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 17 Jul 2016
  *
  */
private[exporter] class SGExporterImpl(
  val repo: SGAndHFRepository,
  val html: HTMLGenerator,
  val fileWriter: HTMLIndexWriter
)(implicit ec: ExecutionContext) extends SGExporter with StrictLogging {

  private val FavoritesHtmlSettings = HtmlSettings(
    indexFileName = "index.html",
    rootIndexTitle = "Favorite Suicide Girls"
  )

  private def favoritesWriterSettings(implicit es: ExporterSettings) = WriterSettings(
    rootFolder = es.favoritesRootFolderPath,
    rewriteEverything = es.rewriteEverything
  )

  private def allWriterSettings(implicit es: ExporterSettings) = WriterSettings(
    rootFolder = es.allMsRootFolderPath,
    rewriteEverything = es.rewriteEverything
  )

  private def newestWriterSettings(implicit es: ExporterSettings) = WriterSettings(
    rootFolder = es.newestRootFolderPath,
    rewriteEverything = es.rewriteEverything
  )

  private val AllHtmlSettings = HtmlSettings(
    indexFileName = "index.html",
    rootIndexTitle = "All Suicide Girls"
  )

  private def updateFavoritesHTML(deltaFavorites: List[M])(implicit ws: ExporterSettings): Future[Unit] = {
    if (deltaFavorites.nonEmpty) {
      for {
        favoritesIndexDelta <- html.createHTMLPageForMs(deltaFavorites)(FavoritesHtmlSettings)
        _ <- fileWriter.writeRootModelIndex(favoritesIndexDelta)(favoritesWriterSettings)
        completeFavoriteRootIndex <- html.createRootIndex(Favorites.names)(FavoritesHtmlSettings)
        _ <- fileWriter.rewriteRootIndexFile(completeFavoriteRootIndex)(favoritesWriterSettings)
      } yield {
        logger.info(s"-- successfully updated DELTA favorites index ${deltaFavorites.length}: @ ${completeFavoriteRootIndex.relativePathAndName}")
      }
    } else {
      logger.info("-- no delta for favorite")
      Future.unit
    }
  }

  private def updateAllHTML(delta: List[M])(implicit ws: ExporterSettings): Future[Unit] = {
    if (delta.nonEmpty) {
      for {
        completeIndex: CompleteIndex <- repo.completeIndex
        allIndexDelta <- html.createHTMLPageForMs(delta)(AllHtmlSettings)
        _ <- fileWriter.writeRootModelIndex(allIndexDelta)(allWriterSettings)
        allRootIndex <- html.createRootIndex(completeIndex.names)(AllHtmlSettings)
        _ <- fileWriter.rewriteRootIndexFile(allRootIndex)(allWriterSettings)
      } yield {
        logger.info(s"--- successfully updated DELTA all model index of: ${delta.length}")
      }
    } else {
      logger.info("-- no delta for normal ms")
      Future.unit
    }
  }

  override def exportHTMLOfOnlyGivenSubsetOfMs(names: List[Name])(implicit ws: ExporterSettings): Future[Unit] = {
    for {
      ms <- repo.find(names)
      favorites: List[M] = ms.filter(m => Favorites.names.contains(m.name))

      _ <- updateFavoritesHTML(favorites)
      _ <- updateAllHTML(ms)
    } yield ()
  }

  override def exportDeltaHTMLOfMs(ms: List[M])(implicit ws: ExporterSettings): Future[Unit] = {
    val favorites: List[M] = ms.filter(m => Favorites.names.contains(m.name))
    for {
      _ <- updateFavoritesHTML(favorites)
      _ <- updateAllHTML(ms)
    } yield ()
  }

  override def exportHTMLIndexOfFavorites(implicit ws: ExporterSettings): Future[Unit] = {
    for {
      ms <- repo.find(Favorites.names)
      favoritesIndex: MRootIndex <- html.createHTMLPageForMs(ms)(FavoritesHtmlSettings)
      _ <- fileWriter.writeRootModelIndex(favoritesIndex)(favoritesWriterSettings)
    } yield ()
  }

  override def exportHTMLIndexOfAllMs(implicit ws: ExporterSettings): Future[Unit] = {
    for {
      ms <- repo.findAll
      allMsIndex: MRootIndex <- html.createHTMLPageForMs(ms)(AllHtmlSettings)
      _ <- fileWriter.writeRootModelIndex(allMsIndex)(allWriterSettings)
    } yield ()
  }

  override def exportLatestForDaysWithDelta(nrOfDays: Int, delta: List[M])(implicit ws: ExporterSettings): Future[Unit] = {
    val today = LocalDate.today()
    val inThePast = today.minusDays(nrOfDays)
    for {
      ms <- repo.aggregateBetweenDays(inThePast, today, delta)
      sortedLatestToEarliest = ms.sortBy(_._1).reverse
      newestMsPage <- html.createNewestPage(sortedLatestToEarliest)
      _ <- fileWriter.rewriteNewestModelPage(newestMsPage)(newestWriterSettings)
    } yield ()
  }

  override def exportLatestForDays(nrOfDays: Int)(implicit ws: ExporterSettings): Future[Unit] = {
    val today = LocalDate.today()
    val inThePast = today.minusDays(nrOfDays)
    for {
      ms <- repo.aggregateBetweenDays(inThePast, today)
      sortedLatestToEarliest = ms.sortBy(_._1).reverse
      newestMsPage <- html.createNewestPage(sortedLatestToEarliest)
      _ <- fileWriter.rewriteNewestModelPage(newestMsPage)(newestWriterSettings)
    } yield ()
  }

  override def prettyPrint(modelName: Name): Future[String] = {
    for {
      model <- repo.find(modelName) map (_.getOrElse(throw ModelNotFoundException(modelName)))
    } yield model match {
      case sg: SG => sg.setsByNewestFirst.toString
      case h: HF => h.setsByNewestFirst.toString
    }
  }


}
