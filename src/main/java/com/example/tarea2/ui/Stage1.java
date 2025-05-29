package com.example.tarea2.ui;

import com.example.tarea2.pubsub.Broker;
import com.example.tarea2.pubsub.Topic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Stage1 extends Application {
    private final Broker broker = new Broker();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pub/Sub Simulator");

        // Creamos la barra de menú
        MenuBar menuBar = new MenuBar();

        // Menú de Publishers
        Menu publisherMenu = new Menu("Publisher");
        MenuItem videoPubItem = new MenuItem("Video");
        videoPubItem.setOnAction(e -> openVideoPublisher());
        publisherMenu.getItems().add(videoPubItem);

        // Menú de Subscribers
        Menu subscriberMenu = new Menu("Subscriber");
        MenuItem videoSubItem = new MenuItem("Video");
        videoSubItem.setOnAction(e -> openVideoFollower());
        subscriberMenu.getItems().add(videoSubItem);

        menuBar.getMenus().addAll(publisherMenu, subscriberMenu);

        // Layout principal
        VBox root = new VBox(menuBar);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openVideoPublisher() {
        // Pedimos nombre al usuario
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Publisher");
        nameDialog.setHeaderText("Enter name for Video Publisher");
        Optional<String> nameOpt = nameDialog.showAndWait();
        if (!nameOpt.isPresent()) return;
        String name = nameOpt.get();

        // Pedimos topic
        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setTitle("Topic");
        topicDialog.setHeaderText("Enter topic for Video Publisher");
        Optional<String> topicOpt = topicDialog.showAndWait();
        if (!topicOpt.isPresent()) return;
        Topic topic = new Topic(topicOpt.get());

        // Creamos e inicializamos el Publisher
        VideoPublisher publisher = new VideoPublisher(name);
        publisher.setBroker(broker);
        publisher.setTopic(topic);

        // Abrimos una nueva ventana para el Publisher
        Stage stage = new Stage();
        stage.setTitle("Publisher: " + name);
        stage.setScene(new Scene(publisher.getView(), 400, 100));
        stage.show();
    }

    private void openVideoFollower() {
        // Pedimos nombre al usuario
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Subscriber");
        nameDialog.setHeaderText("Enter name for Video Follower");
        Optional<String> nameOpt = nameDialog.showAndWait();
        if (!nameOpt.isPresent()) return;
        String name = nameOpt.get();

        // Pedimos topic
        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setTitle("Topic");
        topicDialog.setHeaderText("Enter topic for Video Follower");
        Optional<String> topicOpt = topicDialog.showAndWait();
        if (!topicOpt.isPresent()) return;
        Topic topic = new Topic(topicOpt.get());

        // Creamos e inicializamos el Subscriber
        VideoFollower follower = new VideoFollower(name);
        broker.subscribe(follower, topic);

        // Abrimos una nueva ventana para el Subscriber
        Stage stage = new Stage();
        stage.setTitle("Subscriber: " + name);
        stage.setScene(new Scene(follower.getView(), 400, 100));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}