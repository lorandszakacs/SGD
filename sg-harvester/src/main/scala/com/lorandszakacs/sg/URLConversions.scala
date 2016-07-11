package com.lorandszakacs.sg

import java.net.URL

import akka.http.scaladsl.model.Uri

import scala.language.implicitConversions

/**
  *
  * @author Lorand Szakacs, lorand.szakacs@busymachines.com, lsz@lorandszakacs.com
  * @since 11 Jul 2016
  *
  */
trait URLConversions {

  implicit def sprayURIToJavaURL(uri: Uri): URL = new URL(uri.toString)

  implicit def javaURLtoSprayUri(url: URL): Uri = Uri(url.toExternalForm)

  implicit def stringToURL(str: String): URL = new URL(str)

}