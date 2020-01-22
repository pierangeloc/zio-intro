/*
 * Copyright 2010 TulipLogic BV
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.tuliplogic.ziointro

import zio._
import zio.clock.Clock

import scala.{App => ScalaApp}
import scala.concurrent.{Await, Future}

trait Error
object Error {
  case object UserError extends Error
  case object SystemError extends Error
}


/**
 * Referential transparency and Future
 */
object Futures extends ScalaApp {

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration._
  def program1 = for {
    _ <- Future(println("Hello"))
    _ <- Future(println("Hello"))
  } yield ()

  def program2 = {
    val p1 = Future(println("Hello"))
    for {
      _ <- p1
      _ <- p1
    } yield p1
  }

  Await.result(
    for {
      _ <- Future(println("Run program 1:"))
      _ <- program1
      _ <- Future(println("---------------"))
      _ <- Future(println("Run program 2:"))
      _ <- program2
    } yield (), 2.seconds
  )

}

object ProgramsAsValues extends ScalaApp {
  val p1: Task[Unit] = ZIO.effect(println("Hello"))

  // 1. Build a program that prints hello twice. Referential transparency is back
  // 2. Run a hello world
  // 3. Pure values, effecful values, create errors, absorb errors
  // 4. home made console
  // 5. Real console
  // 6. Explore ZEnv

}

object Combinators extends App {

  import zio.console
  // 1. Anatomy of a ZIO App
  // 2. Sequential execution

  // 3. Sequence a list of ZIO programs
  // 4. Parallel execution of a list of programs

  def p(n: Int) = console.putStrLn(s"run($n)") *> ZIO.succeed(n)
  val prg = ZIO.foreachPar((1 to 100).toList)(p(_))

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] = prg.as(0)
}

object Fibers extends App {

  import zio.random._
  import zio.duration._

  val randomSecondsForAnInt: ZIO[Clock with Random, Nothing, Int] = for {
    n    <- random.nextInt
    secs <- random.nextInt(5)
    res  <- ZIO.sleep(secs.seconds) *> ZIO.succeed(n)
  } yield res

  // 1. How to create a fiber, join, cancel fibers
  val printCurrentTime =
    clock.currentDateTime.flatMap(t => console.putStrLn(s"CurrentTime: $t")).delay(1.seconds).forever

  // 2. Word count with Fibers (source lines parallel counting)
  // 3. Racing
  // 4. Cancellation


  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] =
    (for {
      _ <- console.putStrLn("Printing time")
      fiber <- printCurrentTime.onInterrupt(console.putStrLn(s"I'm dying because I've been interrupted")).fork
      _ <- console.putStrLn("Press a key to stop")
      _ <- console.getStrLn
      _ <- fiber.interrupt
    } yield 0) orElse ZIO.succeed(1)
}

object StreamsExamples extends App {


  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] = ???
}