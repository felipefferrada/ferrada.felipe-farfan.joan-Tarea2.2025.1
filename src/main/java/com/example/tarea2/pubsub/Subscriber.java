package com.example.tarea2.pubsub;

public abstract class Subscriber extends Component {

    public Subscriber() {
        super(); // constructor vacío
    }

    public Subscriber(String name) {
        super(name); // pasa el nombre a la clase Component
    }

    public abstract void onMessage(String message);
}
