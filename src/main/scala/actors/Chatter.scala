package actors

import akka.actor.Actor

class Chatter extends Actor {
    def receive = {
      case CheckInput =>
        if(in.ready
      
    case m => println("Oops in ChatManager: " + m)
  }
}

object Chatter {
  case object CheckInput
}