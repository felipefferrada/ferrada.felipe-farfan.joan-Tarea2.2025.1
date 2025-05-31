# Tarea 2 - Sistema Publicador/Suscriptor con JavaFX

## Integrantes
- Felipe Ferrada
- Joan Farfán

## Repositorio Git:
https://github.com/felipefferrada/ferrada.felipe-farfan.joan-Tarea2.2025.1

## Etapas completadas
- ✅ Etapa 1: Reutilización de clases base (Broker, Publisher, Subscriber, Topic, etc.)
- ✅ Etapa 2: Publisher y Subscriber de Video
- ✅ Etapa 3: GPSCarPublisher y GPSCarFollower con interpolación y Timeline
- ✅ Etapa 4: Documentación de clases Java

## Cómo ejecutar el proyecto
1. Asegúrese de tener Java 21 y JavaFX 17+ configurado.
2. En IntelliJ:
- Abrir el proyecto.
- Usar el archivo pom.xml con Maven.
- VM options para JavaFX: 
- - --module-path /ruta/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.web
3. Ejecutar la clase: Stage1

## Cómo generar la documentación
Desde la terminal del proyecto:
- javadoc \
  --module-path /ruta/javafx-sdk-XX/lib \
  --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.web \
  -d docs \
  -sourcepath src/main/java \
  $(find src/main/java/com/example/tarea2/pubsub -name "*.java") \
  $(find src/main/java/com/example/tarea2/ui -name "*.java")
- Documentación generada en la carpeta docs/.
