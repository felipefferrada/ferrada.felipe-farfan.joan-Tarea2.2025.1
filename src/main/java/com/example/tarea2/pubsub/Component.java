package com.example.tarea2.pubsub;

/**
 * Clase base común para todos los componentes del patrón Pub/Sub.
 * Proporciona almacenamiento y configuración de Broker y Topic.
 */
public abstract class Component {
    protected Broker broker;
    protected Topic topic;
    protected String name;

    /**
     * Constructor por defecto. Inicializa el nombre como cadena vacía.
     */
    public Component() {
        this.name = "";
    }

    /**
     * Constructor que establece el nombre del componente.
     *
     * @param name Nombre identificador del componente.
     */
    public Component(String name) {
        this.name = name;
    }

    /**
     * Configura el broker que gestionará las publicaciones y suscripciones.
     *
     * @param broker Instancia del broker a asignar.
     */
    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    /**
     * Establece el topic en el que este componente publicará o al que se suscribirá.
     *
     * @param topic Topic a asignar al componente.
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * Obtiene el nombre asignado al componente.
     *
     * @return Nombre del componente.
     */
    public String getName() {
        return name;
    }
}
