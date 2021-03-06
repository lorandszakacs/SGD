/**
  * Copyright 2015 Lorand Szakacs
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  */
package com.lorandszakacs.sg.contentparser

import com.lorandszakacs.util.effects._
import com.lorandszakacs.util.time._

import com.lorandszakacs.sg.URLConversions
import com.lorandszakacs.sg.http.SGURLBuilder
import com.lorandszakacs.sg.model._
import com.lorandszakacs.util.html._

import scala.language.postfixOps

object SGContentParser extends SGURLBuilder with URLConversions {
  private val months = Map(
    1  -> "Jan",
    2  -> "Feb",
    3  -> "Mar",
    4  -> "Apr",
    5  -> "May",
    6  -> "Jun",
    7  -> "Jul",
    8  -> "Aug",
    9  -> "Sep",
    10 -> "Oct",
    11 -> "Nov",
    12 -> "Dec",
  ).map(p => p._2 -> p._1)

  /**
    * Source:
    * $domain/girls/valkyria/photos/
    *
    * this filter will yield elements like the following:
    *
    * {{{
    * <header post_id="997826" posttype="album" class="header clearfix">
    * <div class="top-bar">
    * <h2 class="title"> <a href="/girls/moon/album/997826/mirage/">Mirage</a> </h2>
    * <div class="sub-title">
    * <span class="set-type">Set of the day</span>
    * <span class="photographer"> by <a href="/members/cdo/photography/">cdo</a> </span>
    * </div>
    * </div>
    * <div class="sub-header clearfix">
    * <div class="sub-header-container">
    * <div class="meta-data">
    * <time class="time-ago"> Dec 29, 2013 </time>
    * </div>
    * <a id="button-share_997826" type="submit" class="button icon-share has-bar"></a>
    * <a id="likeScore" appname="album" object="album" objectid="997826" direction="clear" class="button like  icon-heart  youLike active">&nbsp;4097</a>
    * </div>
    * <div id="share_997826" class="admin-bar share-links share-menu">
    * </div>
    * <div id="edit_997826" class="admin-bar share-links">
    * <ul class="">
    * </ul>
    * </div>
    * </div>
    * </header>
    * }}}
    */
  def gatherPhotoSetsForM(html: Html): Try[List[PhotoSet]] = Try {
    val albumElement  = Tag("header") && Attribute("post_id")
    val albumElements = html.filter(albumElement)

    val photoSets: List[PhotoSet] = albumElements.map { ae =>
      val html      = Html(ae)
      val urlRepr   = html.filter(Tag("h2") && Class("title") && RetainFirst(Tag("a")) && HrefLink())
      val titleRepr = html.filter(Content(Tag("h2") && Class("title") && RetainFirst(Tag("a"))))
      val timeRepr  = html.filter(Content(Tag("time")))
      val url       = urlRepr.headOption.getOrElse(throw SetRepresentationDidNotContainURLException(html)).trim()
      val title     = titleRepr.headOption.getOrElse(throw SetRepresentationDidNotContainTitleException(html)).trim()
      val date = parseStringToDateTime(
        timeRepr.headOption.getOrElse(throw SetRepresentationDidNotContainTimeTagException(html)).trim(),
      )
      PhotoSet(
        url     = makeFullPathURL(url),
        title   = PhotoSetTitle(title),
        date    = date.get,
        photos  = List.empty,
        isHFSet = if (ae.contains("Hopeful Set")) Option(true) else None,
      )
    }
    photoSets
  }

  /**
    *
    * Source:
    * $domain/photos/
    */
  def gatherNewestPhotoSets(html: Html): Try[List[M]] = {

    /**
      * Example:
      * {{{
      * <section class="image-section">
      * <a href="/girls/risk/album/2751918/purple-and-tea/">
      * <figure data-width="" data-height="" data-ratio="" class="ratio-16-9 res-image  ">
      * <noscript data-tablet="" data-small="https://d1a0n9gptf7ayu.cloudfront.net/cache/98/a3/98a3d45dbdb2b382dcf2da5ada89bbde.jpg" data-mobile="" data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/8a/17/8a17c0d5424afafc10cd249e696be54c.jpg" data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/10/2e/102e8ad4563d4716092d90fd8fb90af7.jpg">
      * <img src="https://d1a0n9gptf7ayu.cloudfront.net/cache/10/2e/102e8ad4563d4716092d90fd8fb90af7.jpg" class="ratio-16-9" alt="">
      * </noscript>
      * </figure>
      * </a>
      * <button type="button" class="icon-hex gallery-view gallery-view-hd" data-album-id="2751918" title="Open Gallery"></button>
      * </section>
      * }}}
      *
      */
    def getPhotoSetLink(html: Html): Try[String] = {
      val potentialLink = html.filter(RetainFirst(Tag("section") && Class("image-section") && HrefLink()))
      potentialLink.headOption match {
        case Some(name) => Try.pure(name)
        case None       => Try.raiseError(HTMLPageDidNotContainAnyPhotoSetLinksException(html))
      }
    }

    /**
      * {{{
      * <time class="time-ago"> Jul 1 </time>
      * }}}
      */
    def getPhotoSetDate(html: Html): Try[LocalDate] = {
      val potentialTime = html.filter(Content(RetainFirst(Tag("time") && Class("time-ago"))))
      potentialTime.headOption match {
        case Some(timeRepr) => parseStringToDateTime(timeRepr)
        case None           => Try.raiseError(SetRepresentationDidNotContainTimeTagException(html))
      }
    }

    /**
      * {{{
      * <a class="facebook-share" href="$domain/members/yessiejune/album/2750189/go-a-little-crazy/" verbose="album" data-facebook-id="508713652527801" data-picture-url="https://d1a0n9gptf7ayu.cloudfront.net/photos/42ec323941a25929a132d317082d0b49.jpg" data-name="Go a Little Crazy!" data-description="Inspired by Harley Quinn">Facebook</a>
      * }}}
      */
    def getPhotoSetTitle(html: Html): Try[PhotoSetTitle] = {
      val potentialTitle = html.filter(Tag("a") && Class("facebook-share") && Value(Attribute("data-name")))
      potentialTitle.headOption match {
        case Some(title) => Try.pure(title)
        case None        => Try.raiseError(SetRepresentationDidNotContainTitleException(html))
      }
    }

    /**
      * {{{
      * <h2 class="title top-bar">
      * <a href="/girls/risk/"> risk </a>
      * <div>
      * <span class="set-type">SG Set</span>
      * <span class="photographer"> by <a href="/girls/saria/">saria</a> </span>
      * </div>
      * </h2>
      * }}}
      */
    def getName(html: Html): Try[Name] = {
      val potentialName = html.filter(Tag("article") && Tag("header") && Tag("h2") && Content(RetainFirst(Tag("a"))))
      potentialName.headOption match {
        case Some(name) => Try.pure(Name(name))
        case None       => Try.raiseError(SetRepresentationDidNotContainNameException(html))
      }
    }

    val elements = html.filter(Tag("article") && Class("type-album"))
    val ms: List[Try[M]] = elements.map { el =>
      val html = Html(el)
      for {
        setURL   <- getPhotoSetLink(html).map(makeFullPathURL)
        setDate  <- getPhotoSetDate(html)
        setTitle <- getPhotoSetTitle(html)
        name     <- getName(html)

        photoSet = PhotoSet(
          url   = setURL,
          title = setTitle,
          date  = setDate,
        )
      } yield {
        if (setURL.toString.contains("members")) {
          HF(
            photoSetURL = photoSetsPageURL(name),
            name        = name,
            photoSets   = List(photoSet),
          )
        }
        else {
          SG(
            photoSetURL = photoSetsPageURL(name),
            name        = name,
            photoSets   = List(photoSet),
          )

        }
      }
    }

    Try(ms.map(_.get))
  }

  def gatherSGNames(html: Html): Try[List[Name]] = {
    html.filter(Class("image-section") && RetainFirst(Tag("a")) && HrefLink()) match {
      case Nil   => Try.raiseError(DidNotFindAnySGProfileLinksException())
      case links => Try.pure(links.map(s => Name(s.replace("/girls/", "").replace("/", ""))))
    }
  }

  def gatherHFNames(html: Html): Try[List[Name]] = {
    html.filter(Class("image-section") && RetainFirst(Tag("a")) && HrefLink()) match {
      case Nil   => Try.raiseError(DidNotFindAnyHFProfileLinksException())
      case links => Try.pure(links.map(s => Name(s.replace("/members/", "").replace("/", ""))))
    }
  }

  /**
    *
    * Looks for elements like this, and extracts:
    * https://d1a0n9gptf7ayu.cloudfront.net/photos/116581ce2839d28a9811f5dd2c9ec3cd.jpg as the [[Photo.url]]
    * https://d1a0n9gptf7ayu.cloudfront.net/cache/07/3b/073b06347bf66229f796072a875ab9f2.jpg [[Photo.thumbnailURL]]
    *
    *
    * {{{
    *      <li class="photo-container" id="thumb-3" data-index="3" data-album-photo-id="3215818">
    *         <a href="https://d1a0n9gptf7ayu.cloudfront.net/photos/116581ce2839d28a9811f5dd2c9ec3cd.jpg">
    *            <figure data-width="" data-height=""  data-ratio="150.753768844" class="ratio-1-1 res-image  ">
    *               <noscript data-tablet="" data-small="" data-mobile="" data-retina="https://d1a0n9gptf7ayu.cloudfront.net/cache/07/3b/073b06347bf66229f796072a875ab9f2.jpg" data-src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b4/73/b473ce6b2cf091cab5872f71a9f715f1.jpg">
    *                  <img src="https://d1a0n9gptf7ayu.cloudfront.net/cache/b4/73/b473ce6b2cf091cab5872f71a9f715f1.jpg" class="ratio-1-1" alt="" >
    *               </noscript>
    *            </figure>
    *         </a>
    *      </li>
    *
    * }}}
    */
  def parsePhotos(albumPage: Html): Try[List[Photo]] = {
    albumPage.filter(Class("image-section") && Tag("li") && Class("photo-container")) match {
      case Nil =>
        Try.raiseError(new Exception(s"Failed to extract any Photo from this document:${albumPage.document.toString}"))
      case photosContainers =>
        Try {
          val photos: List[Photo] = photosContainers.map { pc =>
            val html            = Html(pc)
            val photoIndexOpt   = html.filter(RetainFirst(Value(Attribute("data-index")))) headOption
            val photoURLOpt     = html.filter(RetainFirst(HrefLink())) headOption
            val thumbnailURLOpt = html.filter(Value(Attribute("data-retina"))) headOption
            val opt = for {
              photoIndex   <- photoIndexOpt.map(_.trim.toInt)
              photoURL     <- photoURLOpt
              thumbnailURL <- thumbnailURLOpt
            } yield Photo(
              url          = photoURL,
              thumbnailURL = thumbnailURL,
              index        = photoIndex,
            )
            opt.getOrElse(throw new Exception(s"failed to get Photo out of this html: $pc"))
          }

          photos
        }
    }
  }

  private def parseStringToDateTime(t: String): Try[LocalDate] = {
    val time = t.trim()
    if (t.contains("yesterday")) {
      Try(LocalDate.unsafeYesterday())
    }
    else if (t.contains("hrs")) {
      val nrOfHours = t.replace(" hrs", "")
      Try(LocalDateTime.unsafeNow().minusHours(nrOfHours.toLong)).map(_.toLocalDate)
    }
    else if (t.contains("hr")) {
      val nrOfHours = t.replace(" hr", "")
      Try(LocalDateTime.unsafeNow().minusHours(nrOfHours.toLong)).map(_.toLocalDate)
    }
    else if (t.contains("mins")) {
      val nrOfMinutes = t.replace(" mins", "")
      Try(LocalDateTime.unsafeNow().minusMinutes(nrOfMinutes.toLong)).map(_.toLocalDate)
    }
    else if (t.contains("min")) {
      val nrOfMinutes = t.replace(" min", "")
      Try(LocalDateTime.unsafeNow().minusMinutes(nrOfMinutes.toLong)).map(_.toLocalDate)
    }
    else if (t.contains("now")) {
      Try(LocalDateTime.unsafeNow()).map(_.toLocalDate)
    }
    else {
      Try {
        val datePattern                   = """(\w\w\w) (\d*), (\d\d\d\d)""".r
        val datePattern(month, day, year) = time

        val monthAsInt = months(month)

        val dateTime = LocalDate(year.toInt, monthAsInt, day.toInt)
        dateTime
      }.recoverWith {
        case NonFatal(_) =>
          Try {
            val simplifiedDatePattern             = """(\w\w\w) (\d*)""".r
            val simplifiedDatePattern(month, day) = time
            val monthAsInt                        = months(month)
            LocalDate.unsafeNow().withMonth(monthAsInt).withDayOfMonth(day.toInt)
          }
      }
    }
  }
}
