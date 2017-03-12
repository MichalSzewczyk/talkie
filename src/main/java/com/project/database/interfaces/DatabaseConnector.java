package com.project.database.interfaces;

import com.project.model.User;

import java.util.Optional;

public interface DatabaseConnector {
    Optional<User> getUser(String login);

    Boolean addUser(User user);
}
