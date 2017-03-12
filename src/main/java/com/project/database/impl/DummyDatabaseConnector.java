package com.project.database.impl;

import com.project.database.interfaces.DatabaseConnector;
import com.project.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//TODO: implement database connection logic
@Repository
public class DummyDatabaseConnector implements DatabaseConnector {
    @Override
    public Optional<User> getUser(String login) {
        //database logic
        return Optional.of(new User(login));
    }

    @Override
    public Boolean addUser(User user) {
        //database logic
        return true;
    }
}
