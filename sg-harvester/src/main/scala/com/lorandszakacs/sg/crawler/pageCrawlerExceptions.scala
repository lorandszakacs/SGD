package com.lorandszakacs.sg.crawler

import com.lorandszakacs.util.html.Html

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 03 Jul 2016
  *
  */
final case class FailedToRepeatedlyLoadPageException(offset: Int, cause: Throwable) extends Exception(
  s"Failed while repeatedly opening a page. At offset: $offset. Cause: ${cause.getMessage}",
  cause
)

final case class HTMLPageDidNotContainAnyPhotoSetLinksException(html: Html) extends Exception(
  s"""
     |HTML page did not contain any photo set links:
     |${html.toString}
  """.stripMargin
)

final case class SetRepresentationDidNotContainTimeTagException(html: Html) extends Exception(
  s"""
     |HTML element contain any information about the date of the set:
     |${html.toString}
  """.stripMargin
)

final case class SetRepresentationDidNotContainTitleException(html: Html) extends Exception(
  s"""
     |HTML element contain any information about the title of the set:
     |${html.toString}
  """.stripMargin
)

final case class SetRepresentationDidNotContainModelNameException(html: Html) extends Exception(
  s"""
     |HTML element contain any information about the name of the model:
     |${html.toString}
  """.stripMargin
)

final case class SetRepresentationDidNotContainURLException(html: Html) extends Exception(
  s"""
     |HTML element contain any URL links to set:
     |${html.toString}
  """.stripMargin
)

final case class ExpectedAtLeastOneSetFromHomePageElementsException(html: Html) extends Exception(
  s"""
     |HTML element contain sets:
     |${html.toString}
  """.stripMargin
)

final case class DidNotFindAnySuicideGirlProfileLinksException() extends Exception(
  "Did not find any suicide girls profile links"
)

final case class DidNotFindAnyHopefulProfileLinksException() extends Exception(
  "Did not find any hopeful girls profile links"
)

final case class DidNotFindAnyPhotoLinksOnSetPageException(pageURL: String) extends Exception(
  s"Did not find any photos on page: $pageURL"
)
