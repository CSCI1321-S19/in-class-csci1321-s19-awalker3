package actors

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket
import akka.actor.Props

class ChatManager extends Actor {
  def receive = {
    case CheckAllInput =>
      for(child
    case NewChatter(sock, in, out, name) => 
      context.actorOf(Props(new Chatter(sock, in, out, name)), name)
    
    case m => println("Oops in ChatManager: " + m)
  }
}

object ChatManager {
  case class CheckAllInput
  case class NewChatter(sock: Socket, in: BufferedReader, out: PrintStream, name: String)
}