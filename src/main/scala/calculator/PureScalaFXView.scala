package calculator

import javafx.beans.binding.StringBinding
import scalafx.Includes._
import scalafx.application.{JFXApp, Platform}
import scalafx.event.ActionEvent
import scalafx.geometry.{HPos, Insets}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ComboBox, Label, TextField}
import scalafx.scene.layout.{ColumnConstraints, GridPane, Priority}

class PureScalaFXView(calculates: Operations) extends JFXApp.PrimaryStage {
  title = "Calculator"

  private val types = new ComboBox[Operation]() {
    maxWidth = Double.MaxValue
    margin = Insets(3)
  }

  private val input1 = new TextField {
    margin = Insets(3)
    prefWidth = 200.0
  }

  private val input2 = new TextField {
    prefWidth = 200.0
    margin = Insets(3)
  }

  private val result = new TextField {
    promptText = "0"
    prefWidth = 200.0
    margin = Insets(3)
    editable = false
  }

  scene = new Scene {
    content = new GridPane {
      padding = Insets(5)

      add(new Label("Operator:"), 0, 0)
      add(new Label("Input 1:"), 0, 1)
      add(new Label("Input 2:"), 0, 2)
      add(new Label("Answer:"), 0, 3)

      add(types, 1, 0)
      add(input1, 1, 1)
      add(input2, 1, 2)
      add(result, 1, 3)

      add(new Button("Close") {
        onAction = (e: ActionEvent) => Platform.exit()
      }, 1, 4)

      columnConstraints = List(
        new ColumnConstraints {
          halignment = HPos.LEFT
          hgrow = Priority.SOMETIMES
          margin = Insets(5)
        },
        new ColumnConstraints {
          halignment = HPos.RIGHT
          hgrow = Priority.ALWAYS
          margin = Insets(5)
        }
      )
    }
  }

  for (calc <- calculates.available) {
    types += calc
  }
  types.getSelectionModel.selectFirst()

  result.text <== new StringBinding {
    bind(input1.text.delegate, types.getSelectionModel.selectedItemProperty)
    bind(input2.text.delegate, types.getSelectionModel.selectedItemProperty)
    def computeValue(): String = types.getSelectionModel.getSelectedItem.run(input1.text.value, input2.text.value)
  }

}
