package com.lorandszakacs.sg.crawler.impl.data

import akka.http.scaladsl.model.Uri
import com.github.nscala_time.time.Imports._
import com.lorandszakacs.sg.model._
import com.lorandszakacs.util.html.Html

import scala.io.Source

/**
  *
  * @author Lorand Szakacs, lorand.szakacs@busymachines.com, lsz@lorandszakacs.com
  * @since 04 Jul 2016
  *
  */
trait NewestPhotosPage {
  def html: Html = {
    val resourceName = s"${getClass.getSimpleName.replace("$", "")}.html"
    val URL = getClass.getResource(resourceName)
    val source = Source.fromURL(URL)
    Html(source.getLines().mkString("\n"))
  }

  def currentYear: Int = DateTime.now(DateTimeZone.UTC).getYear

  def numberOfModels: Int

  def models: List[Model]
}

/**
  * DoubleModel = Saria + Talena; i.e. two models in the same set
  */
object NewestPhotosPageWithDoubleModelSet extends NewestPhotosPage {
  override def models: List[Model] = List(
    SuicideGirl(
      photoSetURI = "https://www.suicidegirls.com/girls/saria/photos/view/photosets/",
      name = "Saria",
      photoSets = List(
        PhotoSet(
          url = "https://www.suicidegirls.com/girls/saria/album/2752796/infinite-fire/",
          title = "INFINITE FIRE",
          date = new LocalDate("2016-07-04"),
          photos = Nil
        )
      )
    ),

    SuicideGirl(
      photoSetURI = "https://www.suicidegirls.com/girls/valkyria/photos/view/photosets/",
      name = "valkyria",
      photoSets = List(
        PhotoSet(
          url = "https://www.suicidegirls.com/girls/valkyria/album/2750395/pictures-of-home/",
          title = "PICTURES OF HOME",
          date = new LocalDate("2016-07-04"),
          photos = Nil
        )
      )
    ),

    Hopeful(
      photoSetURI = "https://www.suicidegirls.com/girls/rias/photos/view/photosets/",
      name = "rias",
      photoSets = List(
        PhotoSet(
          url = "https://www.suicidegirls.com/members/rias/album/2749693/cats-blues/",
          title = "CAT'S BLUES",
          date = new LocalDate("2016-07-04"),
          photos = Nil
        )
      )
    ),

    Hopeful(
      photoSetURI = "https://www.suicidegirls.com/girls/insatiableindica/photos/view/photosets/",
      name = "insatiableindica",
      photoSets = List(
        PhotoSet(
          url = "https://www.suicidegirls.com/members/insatiableindica/album/2748045/gamer-girl/",
          title = "GAMER GIRL",
          date = new LocalDate("2016-07-04"),
          photos = Nil
        )
      )
    )
  )

  override def numberOfModels: Int = 24
}
