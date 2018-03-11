/**
  * Copyright 2016 Lorand Szakacs
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
package com.lorandszakacs.sg.app

import akka.actor.ActorSystem
import com.lorandszakacs.sg.app.repl.{CommandLineInterpreter, REPL}
import com.lorandszakacs.util.effects._
import com.typesafe.scalalogging.StrictLogging

/**
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 16 Mar 2015
  *
  */
object Main extends App with StrictLogging {
  implicit val actorSystem: ActorSystem = ActorSystem("sg-app")
  implicit val scheduler:   Scheduler   = Scheduler.io(name = "sg-app")

  val assembly    = new Assembly(actorSystem, scheduler)
  val interpreter = new CommandLineInterpreter(assembly)
  val repl        = new REPL(interpreter, assembly)

  val interpretArgsTask: Task[Unit] = Task(
    logger.info(s"Received args: ${args.mkString(",")} --> executing command")
  ) >>
    interpreter.interpretArgs(args).onError {
      case NonFatal(e) =>
        Task(logger.error("—— something went wrong during interpretation —— exiting", e)) >>
          assembly.shutdownTask >>
          Task(System.exit(1))
    }

  val startReplTask = Task(logger.info(s"Did not receive any arguments, going into REPL mode")) >>
    repl.runTask

  val program = for {
    _ <- if (args.isEmpty) startReplTask else interpretArgsTask
    _ <- assembly.shutdownTask
    _ <- Task(println("... finished gracefully"))
    _ <- Task(System.exit(0))
  } yield ()

  program.asIO.unsafeRunSync()
}
