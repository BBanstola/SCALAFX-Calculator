package calculator

import scalafx.application.JFXApp

object PureScalaFX extends JFXApp{
  stage = new PureScalaFXView(
    new Operations(Addition, Subtraction, Multiplication, Division))

}
