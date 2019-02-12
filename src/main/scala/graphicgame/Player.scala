package graphicgame

class Player (val x:Double, val y: Double, 
val width: Double, val height: Double) extends Entity {
  val level:Level = new Level
}

object Player {
  val level = new Level
}