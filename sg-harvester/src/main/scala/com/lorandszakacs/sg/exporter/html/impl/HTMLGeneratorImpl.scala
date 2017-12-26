package com.lorandszakacs.sg.exporter.html.impl

import com.lorandszakacs.sg.exporter.html._
import com.lorandszakacs.sg.model._
import com.lorandszakacs.util.future._
import com.github.nscala_time.time.Imports._

/**
  *
  * {{{
  *   .
  *   ├── css
  *   │   ├── image_loading.js
  *   │   └── w3.css
  *   ├── icons
  *   │   └── sg_logo.ico
  *   ├── models
  *   │   ├── all
  *   │   ├── favorites
  *   │   └── newest.html
  *   └── scripts
  *       ├── image_loading.js
  *       └── loading_gif.gif
  * }}}
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 17 Jul 2016
  *
  */
private[html] class HTMLGeneratorImpl()(
  implicit val ec: ExecutionContext
) extends HTMLGenerator {

  private val RootPath1 = ".."
  private val RootPath2 = "../.."
  private val RootPath3 = "../../.."

  override def createHTMLPageForMs(ms: List[M])(implicit settings: HtmlSettings): Future[MRootIndex] = {
    for {
      mIndexes <- Future.traverse(ms) { m =>
                   Future(mIndex(m))
                 }
    } yield {
      MRootIndex(
        html = rootIndexPage(mIndexes),
        ms   = mIndexes
      )
    }
  }

  override def createRootIndex(ms: List[Name])(implicit settings: HtmlSettings): Future[Html] = {
    Future.successful(rootIndexPageForNames(ms))
  }

  /**
    *
    * {{{
    *   .
    *   ├── css
    *   │   ├── image_loading.js
    *   │   └── w3.css
    *   ├── icons
    *   │   └── sg_logo.ico
    *   ├── models
    *   │   ├── all
    *   │   ├── favorites
    *   │   └── newest.html
    *   └── scripts
    *       ├── image_loading.js
    *       └── loading_gif.gif
    * }}}
    */
  def createNewestPage(ms: List[(LocalDate, List[M])]): Future[Html] = {
    def newestPageElementForDay(date: LocalDate, models: List[M]): String = {
      val elements = models.sortBy(_.name.name).map { model =>
        val latestSet   = model.photoSets.maxBy(_.date)
        val link        = photoSetPageRelativePathFromCurrentDirectory(model.name, latestSet)
        val displayText = s"${model.name.externalForm} - ${latestSet.title.externalForm}"
        s"""<li><a href="all/$link" target="_blank">$displayText</a></li>"""
      }
      s"""
         |<h3> ${date.toString("YYYY-MM-dd")} </h3>
         |<h3><ol type="1">
         |${elements.mkString("\n")}
         |</ol></h3>
    """.stripMargin

    }

    Future {
      val eachDay = ms.map { p =>
        newestPageElementForDay(p._1, p._2)
      }
      val content =
        s"""
           |<!DOCTYPE html>
           |<html>
           |<title>Newest Sets</title>
           |<head><link rel="icon" href="$RootPath1/icons/sg_logo.ico"></head>
           |  <h3><a href="../index.html">BACK</a></h3>
           |${eachDay.mkString("\n")}
           |</html>
           |
      """.stripMargin
      Html(relativePathAndName = "newest.html", content = content)
    }

  }

  private def mIndex(m: M)(implicit settings: HtmlSettings): MIndex = {
    def iconForPhotoSet(ps: PhotoSet): String = {
      ps.photos.headOption.map(_.thumbnailURL.toExternalForm).getOrElse(s"$RootPath3/icons/sg_logo.ico")
    }

    def iconForModel(m: M): String = {
      m.photoSets.headOption.map(iconForPhotoSet).getOrElse(s"$RootPath3/icons/sg_logo.ico")
    }

    def modelIndexHtmlPage(m: M)(psi: List[PhotoSetIndex])(implicit settings: HtmlSettings): Html = {
      def photoSetLink(photoSet: PhotoSetIndex): String = {
        s"""|<li><a href="../${photoSet.html.relativePathAndName}" target="_blank">${photoSet.displayName}</a></li>
            |""".stripMargin
      }

      Html(
        relativePathAndName = modelIndexPageRelativePathFromCurrentDirectory(m.name),
        content             = s"""
                     |<!DOCTYPE html>
                     |<html>
                     |<title>${m.name.externalForm}</title>
                     |  <head><link rel="icon" href="${iconForModel(m)}"></head>
                     |  <h2><a href="../${settings.indexFileName}">BACK</a></h2>
                     |  <h2>${m.stringifyType.capitalize}: ${m.name.externalForm}</h2>
                     |  <h3>
                     |  <ol type="1">
                     |    ${psi.map(photoSetLink).mkString("\n")}
                     |  </ol>
                     |  </h3>
                     |</html>
    """.stripMargin
      )
    }

    def photoSetIndexPage(m: M)(ps: PhotoSet)(implicit settings: HtmlSettings): PhotoSetIndex = {
      def photoDiv(photo: Photo): String = {
        s"""|<div class="w3-col s3 w3-container">
            |   <a class="w3-hover-opacity", onclick="showImage('${photo.url}');">
            |     <img src="${photo.thumbnailURL.toExternalForm}" alt="${photo.index}" style="width:100%">
            |   </a>
            |</div>""".stripMargin
      }

      val html = Html(
        relativePathAndName = photoSetPageRelativePathFromCurrentDirectory(m.name, ps),
        content             = s"""
                     |<!DOCTYPE html>
                     |<html>
                     |   <title>${m.name.externalForm}: ${ps.title.externalForm}</title>
                     |   <head><link rel="icon" href="${iconForPhotoSet(ps)}"></head>
                     |   <meta name="viewport" content="width=device-width, initial-scale=1">
                     |   <link rel="stylesheet" href="$RootPath3/css/w3.css">
                     |   <script type="text/javascript" src="$RootPath3/scripts/image_loading.js"></script>
                     |   <style>
                     |      .picture {display:none}
                     |   </style>
                     |   <body>
                     |      <div class="w3-container">
                     |         <h2>${m.name.externalForm}: ${ps.title.externalForm} - ${ps.date}</h2>
                     |         <h2><a href="../${modelIndexPageRelativePathFromCurrentDirectory(m.name)}">BACK</a></h2>
                     |      </div>
                     |
                     |      <div class="w3-row">
                     |${ps.photos.map(phs => photoDiv(phs)).mkString("\n")}
                     |      </div>
                     |    <div id="largeImgPanel" onclick="hideMe(this);">
                     |    <img id="largeImg" style="height: 100%; margin: 0; padding: 0;">
                     |   </body>
                     |</html>
    """.stripMargin
      )

      PhotoSetIndex(
        html        = html,
        displayName = s"${ps.date}: ${ps.title.name}"
      )
    }

    val photoSets: List[PhotoSetIndex] = m.photoSetsNewestFirst map photoSetIndexPage(m)
    val modelIndexHtml = modelIndexHtmlPage(m)(photoSets)
    MIndex(
      name      = m.name,
      html      = modelIndexHtml,
      photoSets = photoSets
    )
  }

  private def rootIndexPage(ms: List[MIndex])(implicit settings: HtmlSettings): Html = {
    generateRootIndexPage(ms)(
      title = settings.rootIndexTitle,
      linkAndItemNameGenerator = { m: MIndex =>
        (m.html.relativePathAndName, m.name.name)
      }
    )
  }

  private def rootIndexPageForNames(names: List[Name])(implicit settings: HtmlSettings): Html = {
    generateRootIndexPage(names)(
      title = settings.rootIndexTitle,
      linkAndItemNameGenerator = { m: Name =>
        (modelIndexPageRelativePathFromCurrentDirectory(m), m.name)
      }
    )
  }

  private def generateRootIndexPage[T](
    els:   List[T]
  )(title: String, linkAndItemNameGenerator: T => (String, String))(implicit settings: HtmlSettings): Html = {
    def item(el: T) = {
      val (link, name) = linkAndItemNameGenerator(el)
      s"""<li><a href="$link" target="_blank">$name</a></li>"""
    }

    Html(
      relativePathAndName = settings.indexFileName,
      content             = s"""
                   |<!DOCTYPE html>
                   |<html>
                   |<title>$title</title>
                   |<head><link rel="icon" href="$RootPath2/icons/sg_logo.ico"></head>
                   |  <h3><a href="../../${settings.indexFileName}">BACK</a></h3>
                   |  <h3><ol type="1">
                   |${els.map(item).mkString("\t\t", "\n\t\t", "\n")}
                   |  </ol></h3>
                   |</html>
      """.stripMargin
    )
  }

  private def modelIndexPageRelativePathFromCurrentDirectory(m: Name)(implicit settings: HtmlSettings): String = {
    s"${m.name}/${settings.indexFileName}"
  }

  private def photoSetPageRelativePathFromCurrentDirectory(m: Name, ps: PhotoSet): String = {
    val setName   = s"${m.name}_${ps.date}_${ps.title.name}.html".replaceAll("[^a-zA-Z0-9.-]", "_")
    val modelName = s"${m.name}"
    s"$modelName/$setName"
  }

}
