package graphicgame

class Level {
  val maze: Maze = RandomMaze.apply(10,true,20,30,0.5)
  val entities: Seq[Entity] = Seq[Entity] (Level.p1, Level.p2, Level.e1)
}

object Level {
  
  val p1 = new Player(0.0,0.0,1.0,2.0)
  val p2 = new Player(2.0,0.0,1.0,2.0)
  val e1 = new Enemy(4.0,0.0,1.0,2.0)
  
}