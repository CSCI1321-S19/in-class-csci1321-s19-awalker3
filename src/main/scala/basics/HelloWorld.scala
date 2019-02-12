package basics

/**
 * This is a basic main for you to start off with.
 */
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello World!")
  }

  def square(x: Double) = x * x

  def cube(x: Double) = x * x * x

  val name = "Thomas Page"
  var age = 23

  val message = name + " is " + age + " years old."
  val message1 = s"$name is ${age + 1} years old"

  println(message)
  println(message1)

  val square1 = (x: Double) => x * x
  println(square1(3))

  val twice: Double => Double = _ * 2
}