package actors

import akka.actor.ActorSystem
import akka.actor.Props
import java.net.ServerSocket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream

object ActorChat extends App {
  val system= ActorSystem("ActorChat")
  val chatManager = system.actorOf(Props[ChatManager], "ChatManager")
//  system.scheduler.schedule(0.seconds, 0.1.seconds, chatManager, ChatManager.CheckAllInput
  
  val ss = new ServerSocket(4666)
  while(true) {
    val sock = ss.accept()
    val in = new BufferedReader(new InputStreamReader(sock.getInputStream))
    val out = new PrintStream(sock.getOutputStream)
    out.println("What is your name?")
    val name = in.readLine()
    chatManager ! ChatManager.NewChatter(sock, in, out, name)
  }
}