package com.example.tarea2.pubsub;

public abstract class Subscriber extends Component {
    /** Recibe el mensaje publicado */
    public abstract void onMessage(String message);
}