package com.talkie.utils;

public interface Visitable {
    void accept(HandlingVisitor handlingVisitor);
}
