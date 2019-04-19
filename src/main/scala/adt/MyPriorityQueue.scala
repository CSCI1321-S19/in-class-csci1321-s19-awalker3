package adt

trait MyPriorityQueue[A] {
  def enqueue(o: A): Unit
  def dequeue():A
  def peek:Unit
  def isEmpty:Boolean
}