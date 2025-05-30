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
    private final Broker broker = Broker.getInstance();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pub/Sub Simulator");

        MenuBar menuBar = new MenuBar();

        Menu publisherMenu = new Menu("Publisher");
        MenuItem videoPubItem = new MenuItem("Video");
        videoPubItem.setOnAction(e -> openVideoPublisher());
        publisherMenu.getItems().add(videoPubItem);

        MenuItem gpsPubItem = new MenuItem("Car's GPS");
        gpsPubItem.setOnAction(e -> openGPSPublisher());
        publisherMenu.getItems().add(gpsPubItem);

        Menu subscriberMenu = new Menu("Subscriber");
        MenuItem videoSubItem = new MenuItem("Video");
        videoSubItem.setOnAction(e -> openVideoFollower());
        subscriberMenu.getItems().add(videoSubItem);

        MenuItem gpsSubItem = new MenuItem("Car GPS");
        gpsSubItem.setOnAction(e -> openGPSFollower());
        subscriberMenu.getItems().add(gpsSubItem);

        menuBar.getMenus().addAll(publisherMenu, subscriberMenu);

        VBox root = new VBox(menuBar);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openVideoPublisher() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Publisher");
        nameDialog.setHeaderText("Enter name for Video Publisher");
        Optional<String> nameOpt = nameDialog.showAndWait();
        if (!nameOpt.isPresent()) return;
        String name = nameOpt.get();

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setTitle("Topic");
        topicDialog.setHeaderText("Enter topic for Video Publisher");
        Optional<String> topicOpt = topicDialog.showAndWait();
        if (!topicOpt.isPresent()) return;
        Topic topic = new Topic(topicOpt.get());

        VideoPublisher publisher = new VideoPublisher(name);
        publisher.setBroker(broker);
        publisher.setTopic(topic);
        broker.registerTopic(topic);

        Stage stage = new Stage();
        stage.setTitle("Publisher: " + name);
        stage.setScene(new Scene(publisher.getView(), 400, 100));
        stage.show();
    }

    private void openVideoFollower() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Subscriber");
        nameDialog.setHeaderText("Enter name for Video Follower");
        Optional<String> nameOpt = nameDialog.showAndWait();
        if (!nameOpt.isPresent()) return;
        String name = nameOpt.get();

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setTitle("Topic");
        topicDialog.setHeaderText("Enter topic for Video Follower");
        Optional<String> topicOpt = topicDialog.showAndWait();
        if (!topicOpt.isPresent()) return;
        String topicName = topicOpt.get();
        Topic topic = new Topic(topicName);

        VideoFollower follower = new VideoFollower(name, topicName, broker);
        broker.registerTopic(topic);
        broker.subscribe(topic, follower);

        Stage stage = new Stage();
        stage.setTitle("Subscriber: " + name);
        stage.setScene(new Scene(follower.getView(), 400, 100));
        stage.show();
    }

    private void openGPSPublisher() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Publisher");
        nameDialog.setHeaderText("Enter name for GPS Publisher");
        Optional<String> nameOpt = nameDialog.showAndWait();
        if (!nameOpt.isPresent()) return;
        String name = nameOpt.get();

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setTitle("Topic");
        topicDialog.setHeaderText("Enter topic for GPS Publisher");
        Optional<String> topicOpt = topicDialog.showAndWait();
        if (!topicOpt.isPresent()) return;
        Topic topic = new Topic(topicOpt.get());

        GPSCarPublisher gps = new GPSCarPublisher(name, topic, new Stage());
        Stage stage = new Stage();
        stage.setTitle("GPS Publisher: " + name);
        stage.setScene(new Scene(gps.getView(), 400, 100));
        stage.show();
    }

    private void openGPSFollower() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Subscriber");
        nameDialog.setHeaderText("Enter name for GPS Subscriber");
        Optional<String> nameOpt = nameDialog.showAndWait();
        if (!nameOpt.isPresent()) return;
        String name = nameOpt.get();

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setTitle("Subscriber");
        topicDialog.setHeaderText("Enter topic for GPS Subscriber");
        Optional<String> topicOpt = topicDialog.showAndWait();
        if (!topicOpt.isPresent()) return;
        String topicName = topicOpt.get();
        Topic topic = new Topic(topicName);

        GPSCarFollower follower = new GPSCarFollower(name, topic, broker);
        broker.registerTopic(topic);
        broker.subscribe(topic, follower);

        Stage stage = new Stage();
        stage.setTitle("GPS Subscriber: " + name);
        stage.setScene(new Scene(follower.getView(), 300, 100));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}