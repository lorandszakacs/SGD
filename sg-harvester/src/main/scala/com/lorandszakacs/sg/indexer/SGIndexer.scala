package com.lorandszakacs.sg.indexer

import com.lorandszakacs.sg.http.PatienceConfig
import com.lorandszakacs.sg.model.Model.ModelFactory
import com.lorandszakacs.sg.model._
import org.joda.time.DateTime

import com.lorandszakacs.util.future._

/**
  * Represents the first stage of the pipeline of processing
  *
  * "indexing" can be done without authentication. It implies gathering all
  * model names, and their associated photosets.
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 04 Jul 2016
  *
  */
trait SGIndexer {

  /**
    * Gathers the names of all available [[SuicideGirl]]s
    */
  def gatherSGNames(limit: Int)(implicit pc: PatienceConfig): Future[List[ModelName]]

  /**
    * Gathers the names of all available [[Hopeful]]s
    */
  def gatherHFNames(limit: Int)(implicit pc: PatienceConfig): Future[List[ModelName]]

  /**
    *
    * At the time of writing, there are at most 9 sets displayed per such a page.
    *
    * Check:
    * https://www.suicidegirls.com/girls/dwam/photos/view/photosets/
    * To see if this still holds true.
    *
    * @return
    * the [[PhotoSet]]s of the given model.
    * All elements of the list will have: [[PhotoSet.photos.isEmpty]], and [[PhotoSet.url]] will be a full path URL.
    */
  def gatherPhotoSetInformationForModel[T <: Model](mf: ModelFactory[T])(modelName: ModelName)(implicit pc: PatienceConfig): Future[T]

  /**
    * Similar to [[gatherPhotoSetInformationForModel]], but with more potential for failure
    */
  def gatherPhotoSetInformationForModel(modelName: ModelName)(implicit pc: PatienceConfig): Future[Model]

  /**
    *
    *
    */
  def gatherAllNewModelsAndAllTheirPhotoSets(limit: Int, lastProcessedIndex: Option[LastProcessedMarker])(implicit pc: PatienceConfig): Future[List[Model]]

  final def createLastProcessedIndex(lastModel: Model): LastProcessedMarker = {
    LastProcessedMarker(
      timestamp = DateTime.now(),
      photoSet = lastModel.photoSetsNewestFirst
        .headOption.getOrElse(throw new AssertionError(s"... tried to create last processed index from model ${lastModel.name.name}, but they had no sets")).copy(photos = Nil)
    )
  }

}
