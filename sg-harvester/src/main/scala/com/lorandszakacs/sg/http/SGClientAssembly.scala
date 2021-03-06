package com.lorandszakacs.sg.http

import com.lorandszakacs.sg.http.impl.SGClientImpl

import com.lorandszakacs.util.effects._

/**
  *
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 04 Jul 2016
  *
  */
trait SGClientAssembly {
  def concurrentEffect: ConcurrentEffect[IO]

  implicit def httpIOScheduler: HTTPIOScheduler

  lazy val sgClient: Resource[IO, SGClient] = SGClientImpl()(httpIOScheduler, concurrentEffect)

}
