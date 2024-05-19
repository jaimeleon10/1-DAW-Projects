module dev.jaimeleon.calculadorainterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires logging.jvm;
    requires org.slf4j;


    opens dev.jaimeleon.calculadorainterface to javafx.fxml;
    exports dev.jaimeleon.calculadorainterface;

    opens dev.jaimeleon.calculadorainterface.controllers to javafx.fxml;
    exports dev.jaimeleon.calculadorainterface.controllers;
}