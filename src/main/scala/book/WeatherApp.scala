package book

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.compound.If

object WeatherApp {
  // Build model
  val sunnyToday = Flip(0.2)
  val greetingToday = If(sunnyToday,
    Select(0.6 -> "Hello world!", 0.4 -> "Howdy"),
    Select(0.2 -> "Hello world!", 0.8 -> "Oh no, not again"))
  val sunnyTomorrow = If(sunnyToday, Flip(0.8), Flip(0.05))
  val greetingTomorrow = If(sunnyToday,
    Select(0.6 -> "Hello world!", 0.4 -> "Howdy"),
    Select(0.2 -> "Hello world!", 0.8 -> "Oh no, not again"))

  /** Predict today's greeting using an inference algorithm */
  def predict(): Unit = {
    val result = VariableElimination.probability(greetingToday, "Hello world!")
    println("Today's greeting is \"Hello world!\" with probability " + result + ".")
  }

  /**
    * Use an inference algorithm to infer today weather,
    * given the observation that the greeting is "Hello, World!"
    */
  def infer(): Unit = {
    greetingToday.observe("Hello world!")
    val result = VariableElimination.probability(sunnyToday, true)
    println("If today's greeting is \"Hello world!\". Then it is sunny with probability " + result)
  }

  /**
    * Learn from observing that today greeting is Hello world!
    * to predict tommorow greeting using an inference algorithm
    */
  def learnAndPredict(): Unit = {
    greetingToday.observe("Hello world!")
    val result = VariableElimination.probability(greetingTomorrow, "Hello world!")
    println("If today's greeting is \"Hello world!\". Then tommorow greeting ill be Hello world! with probability " + result)
  }

  def main(args: Array[String]): Unit = {
    predict()
    infer()
    learnAndPredict()
  }
}
