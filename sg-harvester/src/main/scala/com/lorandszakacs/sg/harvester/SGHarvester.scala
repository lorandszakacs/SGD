package com.lorandszakacs.sg.harvester

import com.lorandszakacs.sg.http.{PasswordProvider, PatienceConfig}
import com.lorandszakacs.sg.model._
import com.lorandszakacs.util.future._

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 04 Jul 2016
  *
  */
@scala.deprecated("replaced by non-redundant downloader", "now")
trait SGHarvester {

  /**
    * Fetches all [[SuicideGirl]] names from the website, and updates them in the
    * local repository.
    *
    * @return
    * a list of all the names of the SGs in the index
    */
  def reindexSGNames(maxNrOfSGs: Int)(implicit pc: PatienceConfig): Future[List[ModelName]]

  /**
    * Fetches all [[Hopeful]] names from the website, and updates them in the
    * local repository.
    *
    * @return
    * a list of all the names of the SGs in the index
    */
  def reindexHopefulsNames(maxNrOfHopefuls: Int)(implicit pc: PatienceConfig): Future[List[ModelName]]


  /**
    * The composite of [[reindexSGNames]] and [[reindexHopefulsNames]].
    *
    * Additionally, it will ensure that the latest processing status updated
    */
  def reindexAll(maxNrOfReindexing: Int)(implicit pc: PatienceConfig): Future[List[ModelName]]

  /**
    * Gathers all, or the max number of [[PhotoSet]] from the last harvesting.
    *
    * Effects:
    * - updates: [[SuicideGirlIndex]], and [[HopefulIndex]]
    * - handles "transitions" of [[Hopeful]] to [[SuicideGirl]]
    *
    *
    * It harvests the following page:
    * https://www.suicidegirls.com/photos/
    */
  def gatherNewestPhotosAndUpdateIndex(maxNrOfModels: Int)(implicit pc: PatienceConfig): Future[List[Model]]


  /**
    * Makes sure that every [[Model]] in the index has at least one [[PhotoSet]].
    * If not, it removes the model from the appropriate index, and collection, and
    * stores their name in the [[CleanedUpModelsIndex]]
    */
  def cleanIndex()(implicit pc: PatienceConfig): Future[(List[ModelName], List[ModelName])]


  /**
    * If necessary, will attempt to authenticate with the given username and password.
    *
    * Will gather all [[PhotoSet]]s, with all the [[PhotoSet.photos]] for all the [[SuicideGirl]]s
    * and [[Hopeful]]s whose name can be found in the [[SuicideGirlIndex.needsReindexing]], and
    * [[HopefulIndex.needsReindexing]] collections, respectively. Removes them from there if
    * the gathering of all information was successful.
    *
    */
  def gatherAllDataForSuicideGirlsAndHopefulsThatNeedIndexing(includeProblematic: Boolean)(implicit pc: PatienceConfig, passwordProvider: PasswordProvider): Future[List[Model]]

  /**
    * Same as [[gatherAllDataForSuicideGirlsAndHopefulsThatNeedIndexing]], but for a specific model
    */
  def gatherDataAndUpdateModel(name: ModelName)(implicit pc: PatienceConfig, passwordProvider: PasswordProvider): Future[Model]

  /**
    * same as [[gatherAllDataForSuicideGirlsAndHopefulsThatNeedIndexing]], but it looks at all known [[Model]] in the
    * system.
    */
  def gatherAllDataForSuicideGirlsAndHopefulsFromScratch()(implicit pc: PatienceConfig, passwordProvider: PasswordProvider): Future[List[Model]]

  def authenticateIfNeeded()(implicit passwordProvider: PasswordProvider): Future[Unit]
}
