package com.project.database.impl;

import com.project.database.interfaces.AccessService;
import com.project.database.interfaces.DatabaseConnector;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DatabaseAccessFacade implements AccessService {
    private final DatabaseConnector databaseConnector;

    @Autowired
    public DatabaseAccessFacade(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public User registerUser(User user) {
        user.setSuccess(databaseConnector.addUser(user));
        return user;
    }

    public User loginUser(User user) {
        user.setSuccess(databaseConnector.getUser(user.getLogin()).isPresent());
        return user;
    }

}
