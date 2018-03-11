package com.lorandszakacs.util.future

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 26 Jun 2017
  *
  */
trait AsyncTypes {

  /**
    * convenience aliases, so that you don't have to import this package and [[scala.concurrent.Future]]
    * at the same time
    */
  val Future: concurrent.Future.type = concurrent.Future
  type Future[+T] = concurrent.Future[T]
  val ExecutionContext: concurrent.ExecutionContext.type = concurrent.ExecutionContext
  type ExecutionContext = concurrent.ExecutionContext

  type IO[A] = cats.effect.IO[A]
  val IO: cats.effect.IO.type = cats.effect.IO
}