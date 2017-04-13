package com.talkie.enums;

public enum DatabaseOperationMessage {
    SUCCESS("User successfully logged in."),
    USER_NOT_FOUND("User not exists in database."),
    INCORRECT_PASSWORD("Password is incorrect."),
    USER_ALREADY_EXiSTS("User with this login already exists in database");

    private final String message;

    DatabaseOperationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
