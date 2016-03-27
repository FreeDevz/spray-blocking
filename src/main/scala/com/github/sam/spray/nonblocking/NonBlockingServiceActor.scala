package com.github.sam.spray.nonblocking

import akka.actor._

class NonBlockingServiceActor extends Actor with NonBlockingService {

  def actorRefFactory = context

  def receive = runRoute(routes)
}

