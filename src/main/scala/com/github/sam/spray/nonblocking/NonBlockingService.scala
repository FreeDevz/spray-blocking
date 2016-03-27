package com.github.sam.spray.nonblocking

import akka.actor._
import spray.http.StatusCodes
import spray.routing.HttpService

import scala.util.{Failure, Success}

trait NonBlockingService extends HttpService {

  implicit def executionContext = actorRefFactory.dispatcher

  import akka.pattern.ask

  import scala.concurrent.duration._

  implicit val timeout = akka.util.Timeout(30 seconds)

  val routes = path("nonblock") {
    get {
      parameters('message ? "Hi there") { (message) =>
        println("Count!")
        val waiter = actorRefFactory.actorOf(Props(new Waiter()))
        val future = (waiter ? Waiter.Wait(message))
        onComplete(future) {
          case Success(value) => complete(s"The result was $value")
          case Failure(ex) => complete(StatusCodes.InternalServerError, s"An error occurred: ${ex.getMessage}")
        }
      }
    }
  }
}