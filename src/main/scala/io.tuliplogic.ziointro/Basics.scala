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
