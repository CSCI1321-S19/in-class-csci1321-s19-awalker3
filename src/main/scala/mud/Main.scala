package mud

object Main {
  import scala.io.StdIn._
  def main(args: Array[String]): Unit = {
    var Player = new mud.Player()
    var input = ""
    input = readLine
    while (input != "exit") {
      
      Player.processCommand(input)
      input = readLine
    }      
  }
}