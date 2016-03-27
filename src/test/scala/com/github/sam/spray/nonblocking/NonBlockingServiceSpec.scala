package com.github.sam.spray.nonblocking

import org.scalatest._
import spray.http.StatusCodes._
import spray.testkit.ScalatestRouteTest

class NonBlockingServiceSpec extends FreeSpec with MustMatchers with ScalatestRouteTest with NonBlockingService {
  import scala.concurrent.duration._

  implicit val routeTestTimeout = RouteTestTimeout(10 seconds)

  def actorRefFactory = system

  "Non Blocking" - {

    "get non block" in {
      println("Starting to fire 4 requests")
      val startTime = System.currentTimeMillis()
      1 to 4 foreach { _ =>
        new Thread(){
          Get("/nonblock") ~> routes ~> check {
            status must be (OK)
          }
        }
      }
      val duration = System.currentTimeMillis() - startTime
      println(s"Duration: $duration ms")
    }
  }
}