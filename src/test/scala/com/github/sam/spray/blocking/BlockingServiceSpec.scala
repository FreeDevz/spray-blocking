package com.github.sam.spray.blocking

import org.scalatest._
import spray.http._
import spray.http.ContentTypes._
import spray.http.StatusCodes._
import spray.testkit.ScalatestRouteTest
import akka.actor.Props

class BlockingServiceSpec extends FreeSpec with MustMatchers with ScalatestRouteTest with BlockingService {
  import scala.concurrent.duration._

  implicit val routeTestTimeout = RouteTestTimeout(10 seconds)

  def actorRefFactory = system

  "Blocking" - {

    "get block" in {
      println("Starting to fire 4 requests")
      val startTime = System.currentTimeMillis()
      1 to 4 foreach { _ =>
        Get("/block") ~> routes ~> check {
          status must be (OK)
        }
      }
      val duration = System.currentTimeMillis() - startTime
      println(s"Duration: $duration ms")
    }
  }
}