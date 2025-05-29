package com.example.tarea2.ui;

import com.example.tarea2.pubsub.Subscriber;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VideoFollower extends Subscriber {
    private final VBox view;
    private final Button button;

    public VideoFollower(String name) {
        view = new VBox(10);
        view.setPadding(new Insets(10));
        // Título con el nombre del Subscriber
        Label title = new Label(name);
        // Botón que mostrará la última URL recibida
        button = new Button("No video yet");
        view.getChildren().addAll(title, button);
    }

    @Override
    public void onMessage(String message) {
        // Actualizamos el texto del botón en el hilo de JavaFX
        Platform.runLater(() -> button.setText(message));
    }

    /** Retorna el nodo raíz para incrustar en un Scene */
    public Parent getView() {
        return view;
    }
}