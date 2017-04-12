package com.project.database.impl;

import com.project.database.interfaces.AccessService;
import com.project.database.model.Message;
import com.project.database.model.User;
import com.project.database.repositories.MessageRepository;
import com.project.database.repositories.UserRepository;
import com.project.enums.DatabaseOperationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DatabaseAccessFacade implements AccessService {
    private final Logger logger = LoggerFactory.getLogger(DatabaseAccessFacade.class);
    private static final String USER_LOGGED_IN = "User %s logged in with success.";

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public DatabaseAccessFacade(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
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

    @Override
    public User loginUser(String login, String password) {
        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            user = new User(String.valueOf(false), DatabaseOperationMessage.USER_NOT_FOUND.toString());
        } else if (!password.equals(user.getPassword())) {
            user = new User(String.valueOf(false), DatabaseOperationMessage.INCORRECT_PASSWORD.toString());
        } else {
            user.setOnline(true);
            userRepository.save(user);
            user.setSuccess(String.valueOf(true));
            logger.info(String.format(USER_LOGGED_IN, login));
        }
        return user;
    }

    @Override
    public void logoutUser(Integer login) {
        User user = userRepository.findOne(login);
        user.setOnline(false);
        userRepository.save(user);
    }

    @Override
    public void saveMessage(Integer sender, Integer receiver, Long timestamp, String message) {
        Message entity = new Message(sender, receiver, timestamp, message);
        messageRepository.save(entity);
    }

}
