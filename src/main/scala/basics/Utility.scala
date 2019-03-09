package basics

import adt.ArrayQueue
import collection.mutable

object Utility extends App {
  val maze = Array(
    Array(0, 1, 1, 1, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 1, 0, 1, 1, 0, 0, 0),
    Array(1, 0, 0, 1, 0, 1, 1, 0, 0, 0),
    Array(1, 0, 0, 1, 0, 0, 1, 0, 0, 0),
    Array(1, 1, 0, 1, 0, 0, 1, 0, 0, 0),
    Array(0, 1, 0, 0, 0, 0, 1, 1, 0, 0),
    Array(0, 1, 0, 1, 1, 0, 1, 1, 0, 1),
    Array(0, 0, 0, 0, 1, 0, 1, 1, 0, 0),
    Array(0, 1, 1, 0, 1, 0, 1, 1, 1, 0),
    Array(0, 0, 0, 0, 1, 0, 0, 0, 0, 0))

  val offsets = List((0, -1), (0, 1), (-1, 0), (1, 0))

  
  
  def shortestPath(sx: Int, sy: Int, ex: Int, ey: Int, maze: Array[Array[Int]]): Int = { //ex and ey are player's coordinates. sx and sy are the enemy's coordinates offset by one.
    val queue = new ArrayQueue[(Int, Int, Int)]()
    queue.enqueue((sx, sy, 0)) // assumes the input is valid, probably shouldnt do that
    val visited = mutable.Set[(Int, Int)](sx -> sy)
    while (!queue.isEmpty) {
      val (x, y, steps) = queue.dequeue()
      for ((dx, dy) <- offsets) {
        val nx = x + dx
        val ny = y + dy
        if (nx == ex && ny == ey) return steps // will never be true. nx - ex < 0.0001
        if (nx >= 0 && ny >= 0 && nx < maze.length && ny < maze.length && maze(nx)(ny) == 0 && !visited(nx -> ny)) {
          queue.enqueue((nx, ny, steps + 1))
          //println(nx + ", " + ny)
          visited += nx -> ny
        }
      }
    }
    -1000
  }
  //call shortest path four times (above, below, left, right) and take a step in the direction where it returns the smallest value
  println(shortestPath(0,0,10,0,maze))
  
}



