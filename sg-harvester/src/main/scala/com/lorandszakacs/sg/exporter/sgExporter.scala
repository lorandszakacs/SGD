package com.lorandszakacs.sg.exporter

import java.nio.file.{Path, Paths}

import com.lorandszakacs.sg.model.{M, Name}
import com.lorandszakacs.util.files.FileUtils
import com.lorandszakacs.util.effects._

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 17 Jul 2016
  *
  */
trait SGExporter {
  def prettyPrint(name: Name): IO[String]

  /**
    * Only exports the given [[Name]]s. with the data
    * that is available in the system
    *
    * It will recreate the:
    * ./[[ExporterSettings.favoritesRootFolderPath]]/index.html
    * ./[[ExporterSettings.allMsRootFolderPath]]/index.html
    * Taking into consideration *all* Ms, and *all favorites*, not just the
    * ones specified.
    *
    * But *all* corresponding M specific folders as specified by the M names,
    * will be created.
    *
    * @return
    */
  def exportHTMLOfOnlyGivenSubsetOfMs(ms: List[Name])(implicit ws: ExporterSettings): IO[Unit]

  /**
    * Only exports the given [[M]]s.
    *
    * It will recreate the:
    * ./[[ExporterSettings.favoritesRootFolderPath]]/index.html
    * ./[[ExporterSettings.allMsRootFolderPath]]/index.html
    * Taking into consideration *all* Ms, and *all favorites*, not just the
    * ones specified.
    *
    * But *all* corresponding M specific folders as specified by the M names,
    * will be created.
    *
    * @return
    */
  def exportDeltaHTMLOfMs(ms: List[M])(implicit ws: ExporterSettings): IO[Unit]

  /**
    * Create a navigable HTML webpage at [[ExporterSettings.favoritesRootFolderPath]]
    *
    */
  def exportHTMLIndexOfFavorites(implicit ws: ExporterSettings): IO[Unit]

  /**
    * Create a navigable HTML webpage at [[ExporterSettings.allMsRootFolderPath]]
    */
  def exportHTMLIndexOfAllMs(implicit ws: ExporterSettings): IO[Unit]

  /**
    * Creates and HTML webpage at [[ExporterSettings.newestRootFolderPath]] containing
    * the newest sets, grouped per days, for the past ``nrOfDays``.
    */
  def exportLatestForDays(
    nrOfDays:    Int,
    favorites:   Set[Name],
  )(implicit ws: ExporterSettings): IO[Unit]

  /**
    * Same as [[exportLatestForDays]] but adds in to the already existing Ms
    * the delta passed as parameter
    */
  def exportLatestForDaysWithDelta(
    nrOfDays:  Int,
    delta:     List[M],
    favorites: Set[Name],
  )(
    implicit ws: ExporterSettings,
  ): IO[Unit]

}

object ExporterSettings {

  def apply(
    favoritesRootFolderPath: String,
    allMsRootFolderPath:     String,
    newestRootFolderPath:    String,
    rewriteEverything:       Boolean,
  ): ExporterSettings = {
    new ExporterSettings(
      favoritesRootFolderPath = Paths.get(FileUtils.normalizeHomePath(favoritesRootFolderPath)).toAbsolutePath,
      allMsRootFolderPath     = Paths.get(FileUtils.normalizeHomePath(allMsRootFolderPath)).toAbsolutePath,
      newestRootFolderPath    = Paths.get(FileUtils.normalizeHomePath(newestRootFolderPath)).toAbsolutePath,
      rewriteEverything       = rewriteEverything,
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
final class ExporterSettings private (
  val favoritesRootFolderPath: Path,
  val allMsRootFolderPath:     Path,
  val newestRootFolderPath:    Path,
  val rewriteEverything:       Boolean,
)
