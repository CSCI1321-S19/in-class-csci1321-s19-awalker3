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

object AskPattern extends App {
  case object AskName //no parameters, so use a case object
  case class AskNameOf(other: ActorRef)
  case class NameResponse(name: String)
  implicit val timeout = Timeout(1.seconds)

  
  class AskActor(val name: String) extends Actor {
    def receive = {
      case AskName =>
        Thread.sleep(100)
        sender ! NameResponse(name)
      case AskNameOf(other) =>
        val f = other ? AskName
        f.onComplete {
          case Success(NameResponse(n)) => println("They said their name was " + n)
          case Success(s) => println("They didn't tell us their name.")
          case Failure(ex) => println("Asking their name failed.")
        }
    }
  }

  val system = ActorSystem("SimpleSystem")
  val actor = system.actorOf(Props(new AskActor("Joey")), "AskActor")
  val actor1 = system.actorOf(Props(new AskActor("Elena")), "AskActor1")

  val answer = actor ? AskName

  answer.foreach(n => println("Name is: " + n))
  actor ! AskNameOf(actor1)

  system.terminate()
}