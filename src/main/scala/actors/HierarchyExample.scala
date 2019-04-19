
package actors

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import akka.pattern._
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure

object HierarchyExample extends App {

  case object CreateChild
  case object SignalChildren
  case object PrintSignal

  class ParentActor extends Actor {
    private var number = 0
    private val children = collection.mutable.Buffer[ActorRef]()
    def receive = {
      case CreateChild =>
        children += context.actorOf(Props[ChildActor], "child" + number)
        number += 1
      case SignalChildren =>
        children.foreach(_ ! PrintSignal)
    }
  }
  class ChildActor extends Actor {
    def receive = {
      case PrintSignal => println(self)
    }
  }

  val system = ActorSystem("SimpleSystem")
  val actor = system.actorOf(Props(new ParentActor), "Parent")

  actor ! CreateChild
  actor ! SignalChildren
  actor ! CreateChild
  actor ! CreateChild
  actor ! CreateChild
  actor ! SignalChildren

}