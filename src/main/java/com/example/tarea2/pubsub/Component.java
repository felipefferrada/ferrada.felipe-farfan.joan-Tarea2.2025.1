package com.example.tarea2.pubsub;

public abstract class Component {
    protected Broker broker;
    protected Topic topic;

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}