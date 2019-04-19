package basics

class RecursivePractice {
  
  def fact(n: Int):Int = if (n<1) 1 else n * fact(n-1)
  
  def fib(n: Int):Int = if(n<2) 1 else fib(n-1) + fib(n-2)
  
  def knapsack(items: List[(Double, Double)], weightLeft: Double): Double = items match {
    case Nil => 0.0
    case ((value, weight)) :: t => knapsack(t, weightLeft) max (if(weight > weightLeft) 0.0 else value + knapsack(t, weightLeft - weight))
  }
  
  println(fact(5))
  println(fact(10))
  println(fib(5))
  println(fib(8))
  println(fib(10))
  
}