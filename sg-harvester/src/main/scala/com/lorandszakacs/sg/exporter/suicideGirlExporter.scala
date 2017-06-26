package com.lorandszakacs.sg.exporter

import java.nio.file.{Path, Paths}

import com.lorandszakacs.sg.model.ModelName
import com.lorandszakacs.util.future._

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 17 Jul 2016
  *
  */
trait SGExporter {
  def prettyPrint(modelName: ModelName): Future[String]

  /**
    * Only exports the given [[ModelName]]s.
    *
    * It will recreate the:
    * ./[[ExporterSettings.favoritesRootFolderPath]]/index.html
    * ./[[ExporterSettings.allModelsRootFolderPath]]/index.html
    * Taking into consideration *all* models, and *all favorites*, not just the
    * ones specified.
    *
    * But *all* corresponding model specific folders as specified by the model names,
    * will be created.
    *
    * @return
    */
  def exportDeltaHTMLIndex(ms: List[ModelName])(implicit ws: ExporterSettings): Future[Unit]

  /**
    * Create a navigable HTML webpage at [[ExporterSettings.favoritesRootFolderPath]]
    *
    */
  def exportHTMLIndexOfFavorites(implicit ws: ExporterSettings): Future[Unit]

  /**
    * Create a navigable HTML webpage at [[ExporterSettings.allModelsRootFolderPath]]
    */
  def exportHTMLIndexOfAllModels(implicit ws: ExporterSettings): Future[Unit]

  /**
    * Creates and HTML webpage at [[ExporterSettings.newestRootFolderPath]] containing
    * the newest sets, grouped per days, for the past ``nrOfDays``.
    */
  def exportLatestForDays(nrOfDays: Int)(implicit ws: ExporterSettings): Future[Unit]

  /**
    * The two paths:
    * ``~/suicide-girls/models/favorites/porcelinna/porcelinna_2016-06-11_PEACH_BLOSSOM.html``
    * ``~/suicide-girls/models/favorites/porcelinna/porcelinna_2016-07-19_PEACH_BLOSSOM.html``
    *
    * Differ only in date, because if you keep using [[exportDeltaHTMLIndex]], you will eventually
    * wind up in a situation that the same set was first published on 2016-06-11, and then on 2016-07-19
    * it gets to the front page of the website, and its publishing date gets also changed.
    * But since the file was in our system for almost a month, it's highly likely that the export
    * contains the old file.
    */
  def detectDuplicateFiles(folderRootPath: String): Future[Set[Set[String]]]
}

object ExporterSettings {
  def normalizeHomePath(path: String): String = {
    path.replaceFirst("^~", System.getProperty("user.home"))
  }

  def apply(favoritesRootFolderPath: String, allModelsRootFolderPath: String, newestRootFolderPath: String, rewriteEverything: Boolean): ExporterSettings = {
    new ExporterSettings(
      favoritesRootFolderPath = Paths.get(normalizeHomePath(favoritesRootFolderPath)).toAbsolutePath,
      allModelsRootFolderPath = Paths.get(normalizeHomePath(allModelsRootFolderPath)).toAbsolutePath,
      newestRootFolderPath = Paths.get(normalizeHomePath(newestRootFolderPath)).toAbsolutePath,
      rewriteEverything = rewriteEverything
    )
  }
}


/**
  *
  * @param favoritesRootFolderPath
  * this folder has to exist!
  * @param rewriteEverything
  * if true, then everything in the rootFolderPath will be deleted,
  * then rewritten. if false, then the rootFolderPath has to be empty
  */
final class ExporterSettings private(
  val favoritesRootFolderPath: Path,
  val allModelsRootFolderPath: Path,
  val newestRootFolderPath: Path,
  val rewriteEverything: Boolean
)
