package com.example.tarea2.pubsub;

public abstract class Publisher extends Component {
    private String name;

    public Publisher() {
        // Constructor por defecto
    }

    public Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Llama al broker para publicar el mensaje */
    protected void publish(String message) {
        if (broker == null || topic == null) {
            throw new IllegalStateException("Broker o Topic no configurados");
        }
        broker.publish(topic, message);
    }
}
