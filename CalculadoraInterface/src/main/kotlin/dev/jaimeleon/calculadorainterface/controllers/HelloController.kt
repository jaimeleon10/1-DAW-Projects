package dev.jaimeleon.calculadorainterface.controllers

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField

class HelloController {
    @FXML
    private lateinit var numberField1: TextField

    @FXML
    private lateinit var numberField2: TextField

    @FXML
    private lateinit var sumaButton: Button

    @FXML
    private lateinit var restaButton: Button

    @FXML
    private lateinit var multiplicacionButton: Button

    @FXML
    private lateinit var divisionButton: Button

    @FXML
    private lateinit var resultField: TextField

    @FXML
    private fun initialize() {
        sumaButton.onMouseClicked = EventHandler { onMouseClick("+") }
        restaButton.onMouseClicked = EventHandler { onMouseClick("-") }
        multiplicacionButton.onMouseClicked = EventHandler { onMouseClick("*") }
        divisionButton.onMouseClicked = EventHandler { onMouseClick("/") }
    }

    private fun onMouseClick(operator: String) {
        val num1 = numberField1.text.toDoubleOrNull()
        val num2 = numberField2.text.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            resultField.text = "Error"
        } else {
            when (operator) {
                "+" -> resultField.text = (num1 + num2).toString()
                "-" -> resultField.text = (num1 - num2).toString()
                "*" -> resultField.text = (num1 * num2).toString()
                "/" -> resultField.text = (num1 / num2).toString()
            }
        }
    }
}