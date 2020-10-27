package com.algrothm.exercise.search;

public class Pair<T, T1> {
    T key;
    T1 value;

    Pair(T key, T1 value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public T1 getValue() {
        return value;
    }
}
