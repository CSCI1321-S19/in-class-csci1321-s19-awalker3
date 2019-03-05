package videocode

import java.io.FileInputStream
import java.io.IOException
import java.io.FileNotFoundException

object ReadBytes extends App {
  try {
    LoanPattern.useFileInputStream("src/main/scala/videocode/ReadBytes.scala") { fis =>
      var byte = fis.read()
      while (byte >= 0) {
        print(byte + " ")
        byte = fis.read()
      }
      println()
    }
    val fis = new FileInputStream("src/main/scala/videocode/ReadBytes.scala")

  } catch {
    case e: IOException =>
      println("Something else went wrong with IO.")
      e.printStackTrace()
    case e: FileNotFoundException =>
      println("File isn't here")
      e.printStackTrace()
  }

  /*  try {
    val fis = new FileInputStream("src/main/scala/videocode/ReadBytes.scala")
    try {
      var byte = fis.read()
      while (byte >= 0) {
        print(byte + " ")
        byte = fis.read()
      }
      println()
    } catch {
      case e: IOException =>
        println("Something else went wrong with IO.")
        e.printStackTrace()
    } finally {
      fis.close()
    }
  } catch {
    case e: FileNotFoundException =>
      println("File isn't here")
      e.printStackTrace()
  }*/
}