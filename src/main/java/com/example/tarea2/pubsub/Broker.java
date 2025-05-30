package com.example.tarea2.pubsub;

import java.util.*;

public class Broker {
    private static Broker instance = new Broker();
    private final Map<String, List<Subscriber>> subs = new HashMap<>();

    private Broker() {}

    public static Broker getInstance() {
        return instance;
    }

    public void registerTopic(Topic topic) {
        subs.putIfAbsent(topic.getName(), new ArrayList<>());
    }

    public void subscribe(Topic topic, Subscriber s) {
        subs.computeIfAbsent(topic.getName(), k -> new ArrayList<>()).add(s);
    }

    public void publish(Topic topic, String message) {
        List<Subscriber> list = subs.get(topic.getName());
        if (list != null) {
            for (Subscriber s : list) {
                s.onMessage(message);
            }
        }
    }
}
