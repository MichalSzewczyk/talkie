package com.talkie.utils;

public final class Tuple <Key, Value>{
    private final Key key;
    private final Value value;

    public Tuple(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }
}
