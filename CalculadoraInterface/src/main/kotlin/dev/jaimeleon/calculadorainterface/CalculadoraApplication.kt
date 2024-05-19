package dev.jaimeleon.calculadorainterface

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class CalculadoraApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader({}::class.java.getResource("views/hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 450.0, 500.0)
        stage.apply {
            stage.title = "Calculadora JavaFX"
            stage.scene = scene

            centerOnScreen()
            isResizable = false
            isMaximized = false
        }.show()
    }
}

fun main() {
    Application.launch(CalculadoraApplication::class.java)
}