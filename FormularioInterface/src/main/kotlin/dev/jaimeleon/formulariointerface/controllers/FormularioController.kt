package dev.jaimeleon.formulariointerface.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

class FormularioController {
    @FXML
    private lateinit var nombreText: TextField

    @FXML
    private lateinit var passwordText: PasswordField

    @FXML
    private lateinit var emailText: TextField

    @FXML
    private lateinit var fechaDatePicker: DatePicker

    @FXML
    private lateinit var cursoComboBox: ComboBox<String>

    @FXML
    private lateinit var aceptarButton: Button

    @FXML
    private lateinit var limpiarButton: Button

    @FXML
    private lateinit var cerrarButton: Button

    @FXML
    private fun initialize() {
        comboBoxOptions()
    }

    @FXML
    private fun comboBoxOptions() {
        cursoComboBox.items.addAll(
            "1º DAW",
            "1º DAM",
            "2º DAW",
            "2º DAM"
        )
    }
}