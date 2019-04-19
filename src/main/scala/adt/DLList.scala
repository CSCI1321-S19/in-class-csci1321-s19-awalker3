package adt

import collection.mutable

class DLList[A] extends mutable.Buffer[A] {
  private class Node[A](var data: A, var prev: Node[A], var next: Node[A])
  private val end = new Node[A](null.asInstanceOf[A], null, null)
  end.next = end
  end.prev = end
  private var counter: Int = 0

  def +=(elem: A): this.type = {
    val n = new Node[A](elem, end.prev, end)
    end.prev.next = n
    end.prev = n
    counter += 1
    this
  }
  def +=:(elem: A): this.type = {
    val n = new Node[A](elem, end, end.next)
    end.next.prev = n
    end.next = n
    counter += 1
    this
  }
  
  def apply(n: Int): A = {
    var rover = end.next
    for (i <- 0 until n) {
      rover = rover.next
    }
    rover.data
  }
  def clear(): Unit = {
    end.next = end
    end.prev = end
    counter = 0
  }
  def insertAll(n: Int, elems: Traversable[A]): Unit = {
    var rover = end
//    for (i <- 0 until n - 1) {
      
//    }
  }
  def length: Int = counter
  
  def remove(n: Int): A = {
    require(n>=0 && n<counter)
    counter -= 1
    var rover = end.next
    for (i <- 0 until n) rover = rover.next
    rover.prev.next = rover.next
    rover.next.prev = rover.prev
    val ret = rover
    rover.next = ret.next
    ret.data
  }
  
  def update(n: Int, newelem: A): Unit = {
    require(n>=0 && n<counter)
    var rover = end.next
    for (i <- 0 until n) {
      rover = rover.next
    }
    rover.data = newelem
  }

  def iterator = new Iterator[A] {
    var rover = end.next
    def hasNext: Boolean = rover != end
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }
}


