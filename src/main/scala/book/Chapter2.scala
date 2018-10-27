package book

import com.cra.figaro.library.atomic.continuous.{Normal, Uniform}
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.Flip


object Chapter2 {

  def continuous(args: Array[String]): Unit = {
    val temp = Normal(40, 100)
    println(Importance.probability(temp, (t: Double) => t > 50))
//    println(Importance.probability(temp, greaterThen50))
  }

  def compoundAtom(): Unit = {
    val sunnyTodayProb = Uniform(0, 0.5)
    val sunnyToday = Flip(sunnyTodayProb)
    println(Importance.probability(sunnyToday, true))
  }

  def greaterThen50(d: Double): Boolean = d > 50

}
