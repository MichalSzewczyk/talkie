package com.project.utils;

public final class Tuple <T, S>{
    private final T t;
    private final S s;

    public Tuple(T t, S s) {
        this.t = t;
        this.s = s;
    }

    public T getT() {
        return t;
    }

    public S getS() {
        return s;
    }
}
