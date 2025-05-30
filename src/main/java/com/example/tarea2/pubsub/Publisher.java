package com.example.tarea2.pubsub;

/**
 * Clase abstracta para publishers en el patrón publisher–subscriber.
 * Hereda de Component e implementa la lógica básica de publicación de mensajes.
 */
public abstract class Publisher extends Component {
    private String name;

    /**
     * Constructor por defecto.
     */
    public Publisher() {
        // Constructor por defecto
    }

    /**
     * Constructor que establece el nombre del publisher.
     *
     * @param name Nombre identificador del publisher.
     */
    public Publisher(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre del publisher.
     *
     * @return Nombre del publisher.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Publica un mensaje a través del broker en el topic configurado.
     * Lanza IllegalStateException si no se ha configurado el broker o el topic.
     *
     * @param message Mensaje a publicar.
     * @throws IllegalStateException si broker o topic son nulos.
     */
    protected void publish(String message) {
        if (broker == null || topic == null) {
            throw new IllegalStateException("Broker o Topic no configurados");
        }
        broker.publish(topic, message);
    }
}
