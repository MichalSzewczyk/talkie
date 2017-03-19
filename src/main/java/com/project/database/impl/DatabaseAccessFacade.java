package com.project.database.impl;

import com.project.database.interfaces.AccessService;
import com.project.database.model.User;
import com.project.database.repositories.UserRepository;
import com.project.enums.DatabaseOperationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DatabaseAccessFacade implements AccessService {
    private final UserRepository userRepository;

    @Autowired
    public DatabaseAccessFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String login, String name, String lastName, String password, String avatar, boolean online) {
        User user = userRepository.findOneByLogin(login);
        if (user != null) {
            user.setSuccess(String.valueOf(false));
            user.setMessage(DatabaseOperationMessage.USER_ALREADY_EXiSTS.toString());
        } else {
            user = new User(login, name, lastName, password, avatar, online, String.valueOf(true), DatabaseOperationMessage.SUCCESS.toString());
            userRepository.save(user);
            user.setSuccess(String.valueOf(true));
        }
        return user;
    }

    public User loginUser(String login, String password) {
        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            user = new User(String.valueOf(false), DatabaseOperationMessage.USER_NOT_FOUND.toString());
        } else if (password.equals(user.getPassword())) {
            user = new User(String.valueOf(false), DatabaseOperationMessage.INCORRECT_PASSWORD.toString());
        } else {
            user.setOnline(true);
            userRepository.save(user);
            user.setSuccess(String.valueOf(true));
        }
        return user;
    }

}
