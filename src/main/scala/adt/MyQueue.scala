package adt

trait MyQueue[A] {
  /**
   * Puts an element at the end
   */
  def enqueue(o: A): Unit
  /** 
   *  Takes out the element at the front
   */
  def dequeue():A
  /** 
   *  
   */
  def peek:Unit
  def isEmpty:Boolean
}