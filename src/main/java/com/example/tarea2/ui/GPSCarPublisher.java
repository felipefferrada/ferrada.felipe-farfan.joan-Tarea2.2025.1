package com.example.tarea2.ui;

import com.example.tarea2.pubsub.Publisher;
import com.example.tarea2.pubsub.Topic;
import com.example.tarea2.pubsub.Broker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Publicador de posiciones GPS de un vehículo.
 * Lee un archivo de posiciones, genera puntos interpolados
 * y publica periódicamente la ubicación en un topic.
 */
public class GPSCarPublisher extends Publisher {
    private final VBox view;
    private final Label statusLabel;
    private final List<InterpolatedPoint> points = new ArrayList<>();
    private int currentIndex = 0;
    private Timeline timeline;

    /**
     * Crea un GPSCarPublisher con nombre, topic y stage para la UI.
     * Registra el topic en el broker e inicializa la vista de JavaFX.
     *
     * @param name  Nombre identificador del publisher.
     * @param topic Topic en el que se publicarán los mensajes.
     * @param stage Stage principal de JavaFX para mostrar diálogos de archivo.
     */
    public GPSCarPublisher(String name, Topic topic, Stage stage) {
        super(name);
        this.setBroker(Broker.getInstance());
        this.setTopic(topic);
        Broker.getInstance().registerTopic(topic);

        this.view = new VBox(10);
        this.statusLabel = new Label("Esperando archivo GPS...");
        view.getChildren().add(statusLabel);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo GPS");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            loadAndInterpolatePositions(file);
            startPublishing();
        }
    }

    /**
     * Carga e interpola puntos de posición desde un archivo.
     *
     * @param file Archivo de texto con registros GPS (tiempo, x, y).
     */
    private void loadAndInterpolatePositions(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Position> raw = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("[,\\s]+");
                if (parts.length == 3) {
                    int t = Integer.parseInt(parts[0]);
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    raw.add(new Position(t, x, y));
                }
            }

            points.clear();
            for (int i = 0; i < raw.size() - 1; i++) {
                Position p1 = raw.get(i);
                Position p2 = raw.get(i + 1);
                for (int t = p1.time; t < p2.time; t++) {
                    double alpha = (double) (t - p1.time) / (p2.time - p1.time);
                    double x = p1.x + alpha * (p2.x - p1.x);
                    double y = p1.y + alpha * (p2.y - p1.y);
                    points.add(new InterpolatedPoint(t, x, y));
                }
            }

            Position last = raw.get(raw.size() - 1);
            points.add(new InterpolatedPoint(last.time, last.x, last.y));

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error al leer archivo");
        }
    }

    /**
     * Inicia la publicación periódica de las posiciones interpoladas
     * utilizando un Timeline de JavaFX.
     */
    private void startPublishing() {
        if (points.isEmpty()) {
            statusLabel.setText("No hay puntos para publicar");
            return;
        }

        statusLabel.setText("Publicando...");
        currentIndex = 0;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (currentIndex >= points.size()) {
                statusLabel.setText("Fin de transmisión.");
                timeline.stop();
                return;
            }

            InterpolatedPoint p = points.get(currentIndex);
            String msg = String.format("%d,%.2f,%.2f", p.time, p.x, p.y);
            System.out.println("→ Publicando en tópico: " + topic.getName() + " → " + msg);
            publish(msg);
            statusLabel.setText("Publicando: " + msg);
            currentIndex++;
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Devuelve la vista de JavaFX que muestra el estado y controles.
     *
     * @return Contenedor VBox con la interfaz de este publisher.
     */
    public VBox getView() {
        return view;
    }

    /**
     * Clases internas de soporte
     */
    private static class Position {
        int time;
        double x, y;
        public Position(int time, double x, double y) {
            this.time = time;
            this.x = x;
            this.y = y;
        }
    }

    private static class InterpolatedPoint {
        int time;
        double x, y;
        public InterpolatedPoint(int time, double x, double y) {
            this.time = time;
            this.x = x;
            this.y = y;
        }
    }
}