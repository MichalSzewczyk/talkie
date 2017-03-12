package com.project.database.operations;

@FunctionalInterface
public interface DatabaseCheckOperation<T> {
    boolean performOperation(T t);
}
