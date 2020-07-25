package calculator

trait Operation{
  val description: String
  def run(input1: String, input2: String): String

  override def toString: String = description
}

object Addition extends Operation{
  val description = "Addition"
  def run(input1: String,  input2:String): String =
    try {
      (input1.toDouble + input2.toDouble).toString
    } catch {
      case ex: Throwable => ex.toString
    }
}

object Subtraction extends Operation{
  val description = "Subtraction"
  def run(input1: String,  input2:String): String =
    try {
      (input1.toDouble - input2.toDouble).toString
    } catch {
      case ex: Throwable => ex.toString
    }
}

object Multiplication extends Operation{
  val description = "Multiplication"
  def run(input1: String,  input2:String): String =
    try {
      (input1.toDouble * input2.toDouble).toString
    } catch {
      case ex: Throwable => ex.toString
    }
}

object Division extends Operation{
  val description = "Division"
  def run(input1: String,  input2:String): String =
    try {
      ((input1.toDouble) / (input2.toDouble)).toString
    } catch {
      case ex: Throwable => ex.toString
    }
}




