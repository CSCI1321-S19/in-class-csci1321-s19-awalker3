package adt

import collection.mutable

class SLList[A] extends mutable.Buffer[A] {
  private class Node[A](var data: A, var next: Node[A])
  private var hd: Node[A] = null
  private var tl: Node[A] = null
  private var counter: Int = 0

  def +=(elem: A): this.type = {
    val n = new Node[A](elem, null)
    if(hd == null) {
      hd = n
    } else {
      tl.next = n
    }
    tl = n
    counter += 1
    this
  }
  def +=:(elem: A): this.type = ???
  def apply(n: Int): A = {
    var rover = hd
    for (i <- 0 until n) {
      rover = rover.next
    }
    rover.data
  }
  def clear(): Unit = {
    hd = null
    tl = null
    counter = 0
  }
  def insertAll(n: Int, elems: Traversable[A]): Unit = {
    var rover = hd
    for (i <- 0 until n - 1) {

    }
  }
  def length: Int = counter
  def remove(n: Int): A = {
    counter -= 1
    if (n == 0) {
      val ret = hd.data
      hd = hd.next
      if (hd == null) tl == null
      ret
    } else {
      var rover = hd
      for (i <- 0 until n - 1) {
        rover = rover.next
      }
      if (rover.next == tl) tl = rover
      val ret = rover.next
      rover.next = ret.next
      ret.data
    }
  }
  def update(n: Int, newelem: A): Unit = {
    var rover = hd
    for (i <- 0 until n) {
      rover = rover.next
    }
    rover.data = newelem
  }

  def iterator = new Iterator[A] {
    var rover = hd
    def hasNext: Boolean = rover != null
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }
}


