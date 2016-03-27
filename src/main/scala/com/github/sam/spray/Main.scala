package com.github.sam.spray

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.github.sam.spray.blocking.BlockingServiceActor
import com.github.sam.spray.nonblocking.NonBlockingServiceActor
import spray.can.Http

object Main extends App {

  implicit val system = ActorSystem()

  val blockService = system.actorOf(Props[BlockingServiceActor], "blockService")
  val nonblockService = system.actorOf(Props[NonBlockingServiceActor], "nonblockService")

  //IO(Http) ! Http.Bind(blockService, "0.0.0.0", port = 9000)
  IO(Http) ! Http.Bind(nonblockService, "0.0.0.0", port = 9000)

}