package com.algrothm.exercise.utils;

public interface TestCase<T> {
    T getExceptedResult();

    default String resultToString(T t) {
        return String.valueOf(t);
    }
}
