package com.example.queststore.models;

public class ExpLevel implements Entry {

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

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Level name: %s, Value: %d", name, value);
    }
}