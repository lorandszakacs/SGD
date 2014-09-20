/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Lorand Szakacs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.lorandszakacs.sgd.app

import scala.io.StdIn
import scala.language.postfixOps
import scala.util.{ Failure, Success }
import com.lorandszakacs.sgd.http.SGClient
import akka.actor.ActorSystem
import spray.http.Uri
import scala.concurrent.Await
import scala.concurrent.duration._

object TestSGClientWithAuthentication extends App {
  implicit val system = ActorSystem("test-login-client")
  import system.dispatcher

  val user = { print("user:"); StdIn.readLine() }
  val pwd = { print("pwd:"); val result = StdIn.readLine(); println(); result }
  val sgClient = SGClient(user, pwd) match {
    case Success(client) =>
      println(client.authentication.toString)
      client
    case Failure(e) =>
      throw e
  }

  def sgShallow(name: String) = {
    val result = Await.result(sgClient.getSuicideGirlShallow(name), 2 minute).get
    println(result.toString)
    result
  }

  def sg(name: String) = {
    val result = Await.result(sgClient.getSuicideGirl(name), 2 minute).get
    println(result.toString)
    result
  }

  sg("Dwam")
  sg("Evette")

  system.shutdown()
}