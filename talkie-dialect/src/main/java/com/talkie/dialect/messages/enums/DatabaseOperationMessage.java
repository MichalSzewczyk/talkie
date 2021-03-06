package com.talkie.dialect.messages.enums;

public enum DatabaseOperationMessage {
    SUCCESS("UserModel successfully logged in."),
    USER_NOT_FOUND("UserModel not exists in database."),
    INCORRECT_PASSWORD("Password is incorrect."),
    USER_ALREADY_EXiSTS("UserModel with this login already exists in database");

    private final String message;

    DatabaseOperationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
