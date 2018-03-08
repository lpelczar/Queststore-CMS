package com.example.queststore.models;

import java.util.Objects;

public class ExpLevel {

    private String name;
    private int value;

    public ExpLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Level name: %s, Value: %d", name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpLevel expLevel = (ExpLevel) o;
        return value == expLevel.value &&
                Objects.equals(name, expLevel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
