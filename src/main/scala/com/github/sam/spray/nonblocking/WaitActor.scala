package com.github.sam.spray.nonblocking

import akka.actor.{Actor, ActorRef}
import akka.pattern.{ pipe }

import scala.concurrent.Future

object Waiter {
  case class Wait(message: String)
  case class Set(waiter: ActorRef)
}

class Waiter extends Actor {

  import context._

  import scala.concurrent.duration._

  var count = 0

  def receive = {
    case Waiter.Wait(message) =>
      count += 1
      println(s"Count should be always 1 here : $count")
      //val delayed = context.system.scheduler.scheduleOnce(5 seconds, sender, s"$count ${self}: $message")
      //Future(delayed) pipeTo sender
      //context.system.scheduler.scheduleOnce(5 seconds, sender, s"$count ${self}: $message")
      Thread.sleep(5000)
      Future {s"This is the $message"} pipeTo sender
  }
}
