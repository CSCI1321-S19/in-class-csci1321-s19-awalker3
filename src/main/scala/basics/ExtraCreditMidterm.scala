package basics
/**
 *  This function creates an array of objects and an array of bins, and sees if the objects can be arranged to fit within the bins.
 *  The objects can go in any bin, but the entire object must be able to fit in one bin in order for it to work.
 *  Creates the arrays in the object for testing, but arrays can be creates elsewhere and passed in.
 *  Created on 3/24/2019 for the Extra Credit take home problem for Lewis' CS2 SP2019.
 */
object ExtraCreditMidterm extends App {

  val objSize: Array[Double] = Array.tabulate(20)(i => (i / 2.0))
  val binSize = Array.tabulate(10)(i => (i + 2) * 2.5)

  def fitObjectsInBins(binSize: Array[Double], objSize: Array[Double]): Boolean = {
    if (objSize.length == 0) return true
    if (objSize.max > binSize.max) return false
    else {
      binSize(binSize.indexOf(binSize.max)) = binSize.max - objSize.max
      var newObjSize = objSize.filterNot(i => i == objSize.max)
      fitObjectsInBins(binSize: Array[Double], newObjSize: Array[Double])
    }
  }

  def classFitObjectsInBins(binSize: Array[Double], objSize: List[Double]): Boolean = objSize match {
    case Nil => true
    case obj :: t => 
      binSize.indices.exists { i => 
        obj <= binSize(i) && {
          binSize(i) -= obj
          val ret = classFitObjectsInBins(binSize, t)
          binSize(i) += obj
          ret
        }
      }
  }

  def fitObjectsInBinsLong(binSize: Array[Double], objSize: Array[Double]): Boolean = {
    if (objSize.length == 0) return true
    if (objSize.max > binSize.max) return false
    else {
      var bigbinIdx = binSize.indexOf(binSize.max)
      binSize(bigbinIdx) = binSize.max - objSize.max
      var newObjSize = objSize.filterNot(i => i == objSize.max)
      println("     Bins minus largest: " + binSize.mkString(", "))
      println("Objects without largest: " + newObjSize.mkString(", "))
      fitObjectsInBinsLong(binSize: Array[Double], newObjSize: Array[Double])
    }
  }
  println("		   Bins: " + binSize.mkString(", "))
  println("		Objects: " + objSize.mkString(", "))
  println(fitObjectsInBins(binSize, objSize))
}