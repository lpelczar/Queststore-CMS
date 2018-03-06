package com.example.queststore.services;

public class Iterator<T> {

    private int index;
    private T[] data;

    public Iterator(T[] elements) {
        data = elements;
    }

    public boolean hasNext() { return index < data.length; }

    public T next() {
        return data[index++];
    }
}
