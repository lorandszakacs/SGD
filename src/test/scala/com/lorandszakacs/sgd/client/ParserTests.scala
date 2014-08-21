package com.lorandszakacs.sgd.client

import scala.util.{ Failure, Success }

import org.scalatest.{ FlatSpec, Matchers }

import com.lorandszakacs.sgd.client.data._
import com.lorandszakacs.sgd.http.Parser

class ParserTests extends FlatSpec with Matchers {

  behavior of "Parser"

  it should "return all the links from a PhotoSetPage" in {
    val expected = PhotoSetPageFullDate
    Parser.parsePhotos(expected.html) match {
      case Success(result) => result should have length expected.numberOfPhotos
      case Failure(e) => fail("did not return any photos", e)
    }
  }

  it should "return a PhotoSet object from a page with a full date" in {
    val expected = PhotoSetPageFullDate
    Parser.parsePhotoSetPage(expected.html, expected.uri) match {
      case Success(result) =>
        result.photos should have length expected.numberOfPhotos
      case Failure(e) =>
        fail("did not return any photos", e)
    }
  }
}