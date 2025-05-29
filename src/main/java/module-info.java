module com.example.tarea2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.tarea2 to javafx.fxml;
    exports com.example.tarea2;
    exports com.example.tarea2.pubsub;
    exports com.example.tarea2.ui;
}