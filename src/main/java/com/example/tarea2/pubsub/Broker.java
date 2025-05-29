package com.example.tarea2.pubsub;

import java.util.*;

public class Broker {
    private final Map<Topic, List<Subscriber>> subscribers = new HashMap<>();

    public void subscribe(Subscriber sub, Topic topic) {
        subscribers
                .computeIfAbsent(topic, t -> new ArrayList<>())
                .add(sub);
        sub.setBroker(this);
        sub.setTopic(topic);
    }

    public void publish(Topic topic, String message) {
        List<Subscriber> subs = subscribers.get(topic);
        if (subs != null) {
            for (Subscriber s : subs) {
                s.onMessage(message);
            }
        }
    }
}