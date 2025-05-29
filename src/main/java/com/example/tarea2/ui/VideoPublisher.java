package com.example.tarea2.ui;

import com.example.tarea2.pubsub.Publisher;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VideoPublisher extends Publisher {
    private final VBox view;

    public VideoPublisher(String name) {
        view = new VBox(10);
        view.setPadding(new Insets(10));
        // Título con el nombre del Publisher
        Label title = new Label(name);
        // Campo para ingresar URL y pulsar ENTER
        TextField textField = new TextField();
        textField.setPromptText("Enter video URL and press Enter");
        textField.setOnAction(e -> {
            publish(textField.getText());  // publica al broker
            textField.clear();
        });
        view.getChildren().addAll(title, textField);
    }

    /** Retorna el nodo raíz para incrustar en un Scene */
    public Parent getView() {
        return view;
    }
}