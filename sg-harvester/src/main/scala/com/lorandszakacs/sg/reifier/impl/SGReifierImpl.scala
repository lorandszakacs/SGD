package com.lorandszakacs.sg.reifier.impl

import com.lorandszakacs.util.effects._
import java.net.URL

import com.lorandszakacs.sg.contentparser.SGContentParser
import com.lorandszakacs.sg.http._
import com.lorandszakacs.sg.model.M._
import com.lorandszakacs.sg.model._
import com.lorandszakacs.sg.reifier._
import com.lorandszakacs.util.logger._

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 05 Jul 2016
  *
  */
private[reifier] class SGReifierImpl(
  private val sGClient:   SGClient,
  private val sessionDao: SessionDaoImpl,
) extends SGReifier with SGURLBuilder {

  implicit private val logger: Logger[IO] = Logger.getLogger[IO]

  implicit private[this] var _authentication: Authentication = DefaultSGAuthentication

  override def authenticateIfNeeded(): IO[Authentication] = {
    if (authentication.needsRefresh) {
      for {
        _               <- logger.info("need to authenticate")
        possibleSession <- sessionDao.find
        newAuth <- possibleSession match {
          case Some(session) =>
            val recreate = for {
              _    <- logger.info("attempting to recreate authentication from stored session")
              auth <- sGClient.createAuthentication(session)
            } yield auth
            recreate

          // val result = recreate recoverWith {
          //   case e: FailedToVerifyNewAuthenticationException =>
          //     logger.error(
          //       "failed to verify stored session, defaulting to using username and password",
          //       e
          //     )
          //     authenticateWithUsernameAndPassword(passwordProvider)
          // }
          //
          // result map { r: Authentication =>
          //   logger.info("successfully restored authentication")
          //   r
          // }

          case None => IO.raiseError(NoSessionFoundException)
        }
      } yield {
        _authentication = newAuth
        newAuth
      }
    }
    else {
      IO.pure(authentication)
    }
  }

  override def reifySG(sg: SG)(implicit pc: PatienceConfig): IO[SG] = {
    reifyM(SGFactory)(sg)
  }

  override def reifyHF(hf: HF)(implicit pc: PatienceConfig): IO[HF] = {
    reifyM(HFFactory)(hf)
  }

  override def reifyM(m: M)(implicit pc: PatienceConfig): IO[M] = {
    m match {
      case sg: SG => reifyM(SGFactory)(sg)
      case hf: HF => reifyM(HFFactory)(hf)
    }

  }

  private def gatherAllPhotosFromSetPage(photoSetPageUri: URL): IO[List[Photo]] = {
    for {
      photoSetPageHTML <- sGClient.getPage(photoSetPageUri)
      photos <- IO.fromTry(SGContentParser.parsePhotos(photoSetPageHTML)).recoverWith {
        case NonFatal(_) => IO.raiseError(DidNotFindAnyPhotoLinksOnSetPageException(photoSetPageUri))
      }

    } yield photos
  }

  private def reifyM[T <: M](mf: MFactory[T])(m: T)(implicit pc: PatienceConfig): IO[T] = {
    def reifyPhotoSets(photoSet: PhotoSet): IO[PhotoSet] = {
      pc.throttleQuarterAfter {
        for {
          photos <- this.gatherAllPhotosFromSetPage(photoSet.url).recoverWith {
            case _: DidNotFindAnyPhotoLinksOnSetPageException =>
              logger.warn(
                s"SGReifier --> reifying: ${photoSet.url} has no photos. `${mf.name} ${m.name.name}`",
              ) >>
                IO.pure(Nil)
            case e: Throwable =>
              logger.error(e)(
                s"SGReifier --> reifying: ${photoSet.url} failed to get parsed somehow. WTF?. `${mf.name} ${m.name.name}`",
              ) >> IO.pure(Nil)
          }
          _ <- logger.info(s"SGReifier --> reified: ${mf.name} ${m.name.name} photoset: ${photoSet.url}")
        } yield photoSet.copy(photos = photos)
      }
    }
    for {
      _      <- logger.info(s"SGReifier --> reifying: ${mf.name} ${m.name.name}. Expecting ${m.photoSets.length} sets")
      result <- m.photoSets.traverse(reifyPhotoSets)
      _      <- logger.info(s"reified ${mf.name} ${m.name.name}. Found ${result.length} photo sets.")
    } yield mf.apply(
      photoSetURL = m.photoSetURL,
      name        = m.name,
      photoSets   = result,
    )

  }

  override def authentication: Authentication = _authentication
}
