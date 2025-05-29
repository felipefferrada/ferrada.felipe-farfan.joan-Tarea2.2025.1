package com.example.tarea2.pubsub;

import java.util.Objects;

public class Topic {
    private final String name;

    public Topic(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Topic)) return false;
        return name.equals(((Topic)o).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}