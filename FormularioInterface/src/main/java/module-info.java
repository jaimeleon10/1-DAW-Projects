module dev.jaimeleon.formulariointerface {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens dev.jaimeleon.formulariointerface to javafx.fxml;
    exports dev.jaimeleon.formulariointerface;

    opens dev.jaimeleon.formulariointerface.controllers to javafx.fxml;
    exports dev.jaimeleon.formulariointerface.controllers;
}