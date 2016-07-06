package com.lorandszakacs.sg.crawler

import com.lorandszakacs.sg.http.Authentication
import com.lorandszakacs.sg.model.Photo

import scala.concurrent.Future

/**
  *
  * @author Lorand Szakacs, lorand.szakacs@busymachines.com, lsz@lorandszakacs.com
  * @since 05 Jul 2016
  *
  */
trait PhotoMediaLinksCrawler {

  /**
    * Warning, this is a stateful method!
    *
    * It will only be applied if [[authentication.needsRefresh]] is true.
    *
    * Its result is that a new [[Authentication]] is recreated where [[authentication.needsRefresh]]
    * will be false
    *
    */
  def authenticateIfNeeded(username: String, plainTextPassword: String): Future[Authentication]

  def gatherAllPhotosFromSetPage(photoSetPageUri: String): Future[List[Photo]]

  def authentication: Authentication
}