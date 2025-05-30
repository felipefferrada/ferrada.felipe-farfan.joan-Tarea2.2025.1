package com.example.tarea2.ui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoPlayer {

    public static void reproducir(String url) {
        try {
            Media media = new Media(url);
            MediaPlayer player = new MediaPlayer(media);
            MediaView mediaView = new MediaView(player);

            StackPane root = new StackPane();
            root.getChildren().add(mediaView);

            Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Reproduciendo video...");
            stage.setScene(scene);
            stage.show();

            player.play();
        } catch (Exception e) {
            System.err.println("Error al reproducir el video: " + e.getMessage());
        }
    }
}
