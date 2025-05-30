package com.example.tarea2.ui;

import com.example.tarea2.pubsub.Broker;
import com.example.tarea2.pubsub.Subscriber;
import com.example.tarea2.pubsub.Topic;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GPSCarFollower extends Subscriber {
    private final VBox view;
    private final Label timeLabel;
    private final Label positionLabel;

    public GPSCarFollower(String name, Topic topic, Broker broker) {
        super(name);
        this.view = new VBox(10);
        this.timeLabel = new Label("Tiempo: --");
        this.positionLabel = new Label("Posición: (--, --)");
        view.getChildren().addAll(timeLabel, positionLabel);

        this.setBroker(broker);
        this.setTopic(topic);
        broker.registerTopic(topic);
        broker.subscribe(topic, this);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("✔ GPSCarFollower recibió mensaje: " + message);
        String[] parts = message.split(",");
        if (parts.length != 3) return;

        String t = parts[0];
        String x = parts[1];
        String y = parts[2];

        Platform.runLater(() -> {
            timeLabel.setText("Tiempo: " + t + " s");
            positionLabel.setText(String.format("Posición: (%s, %s)", x, y));
        });
    }

    public VBox getView() {
        return view;
    }
}