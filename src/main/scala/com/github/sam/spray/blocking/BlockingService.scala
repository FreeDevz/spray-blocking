package com.github.sam.spray.blocking

import spray.routing.HttpService
import akka.actor._
import scala.concurrent.Future
import akka.routing.RoundRobinRouter

trait BlockingService extends HttpService {

  implicit def executionContext = actorRefFactory.dispatcher

  import scala.concurrent.duration._
  import akka.pattern.ask
  implicit val timeout = akka.util.Timeout(30 seconds)

  val waiter = actorRefFactory.actorOf(Props[Waiter])

  val routes = path("block") {
    get {
      parameters('message ? "Hi there") { (message) =>
        complete ({
          (waiter ? Waiter.Wait(message)).mapTo[String]
        })
      }
    }
  }
}