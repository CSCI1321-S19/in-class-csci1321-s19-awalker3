package adt

trait MyStack[A] {
  def push(o: A): Unit
  def pop():A
  def peek:A
  def isEmpty:Boolean
  
}