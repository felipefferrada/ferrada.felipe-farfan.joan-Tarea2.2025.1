package com.example.tarea2.pubsub;

public abstract class Component {
    protected Broker broker;
    protected Topic topic;
    protected String name;

    public Component() {
        this.name = "";
    }

    public Component(String name) {
        this.name = name;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }
}
