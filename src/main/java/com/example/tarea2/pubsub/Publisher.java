package com.example.tarea2.pubsub;

public abstract class Publisher extends Component {
    /** Llama al broker para publicar el mensaje */
    protected void publish(String message) {
        if (broker == null || topic == null) {
            throw new IllegalStateException("Broker o Topic no configurados");
        }
        broker.publish(topic, message);
    }
}