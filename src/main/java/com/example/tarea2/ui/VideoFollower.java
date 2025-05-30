package com.example.tarea2.ui;

import com.example.tarea2.pubsub.Broker;
import com.example.tarea2.pubsub.Subscriber;
import com.example.tarea2.pubsub.Topic;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class VideoFollower extends Subscriber {
    private Button videoButton;
    private HBox view;
    private final String topic;
    private final Broker broker;

    public VideoFollower(String name, String topic, Broker broker) {
        super(); // Tu clase Subscriber ya no acepta parámetros
        this.topic = topic;
        this.broker = broker;

        this.videoButton = new Button("Esperando publicación...");
        this.view = new HBox(10);
        this.view.getChildren().add(videoButton);

        videoButton.setOnAction(e -> {
            String url = videoButton.getText();
            if (!url.startsWith("http")) return;
            VideoPlayer.reproducir(url);
        });

        broker.subscribe(new Topic(topic), this);
    }

    @Override
    public void onMessage(String message) {
        videoButton.setText(message);
    }

    public HBox getView() {
        return view;
    }
}
