package dev.jaimeleon.formulariointerface

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class FormularioApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader({}::class.java.getResource("views/hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 500.0, 500.0)
        stage.apply {
            stage.title = "Registro"
            stage.scene = scene

            centerOnScreen()
            isMaximized = false
            isResizable = false
        }.show()
    }
}

fun main() {
    Application.launch(FormularioApplication::class.java)
}