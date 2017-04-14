package com.talkie.database.impl;

import com.talkie.database.interfaces.AccessService;
import com.talkie.database.model.Message;
import com.talkie.database.model.UserModel;
import com.talkie.database.repositories.MessageRepository;
import com.talkie.database.repositories.UserRepository;
import com.talkie.enums.DatabaseOperationMessage;
import com.talkie.graphql.model.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class DatabaseAccessFacade implements AccessService {
    private final Logger logger = LoggerFactory.getLogger(DatabaseAccessFacade.class);
    private static final String USER_LOGGED_IN = "UserModel %s logged in with success.";

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public DatabaseAccessFacade(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public UserModel registerUser(String login, String name, String lastName, String password, String avatar, boolean online) {
        UserModel userModel = userRepository.findOneByLogin(login);
        if (userModel != null) {
            userModel.setSuccess(String.valueOf(false));
            userModel.setMessage(DatabaseOperationMessage.USER_ALREADY_EXiSTS.toString());
        } else {
            userModel = new UserModel(login, name, lastName, password, avatar, online, String.valueOf(true), DatabaseOperationMessage.SUCCESS.toString());
            userRepository.save(userModel);
            userModel.setSuccess(String.valueOf(true));
        }
        return userModel;
    }

    @Override
    public UserModel loginUser(String login, String password) {
        UserModel userModel = userRepository.findOneByLogin(login);
        if (userModel == null) {
            userModel = new UserModel(String.valueOf(false), DatabaseOperationMessage.USER_NOT_FOUND.toString());
        } else if (!password.equals(userModel.getPassword())) {
            userModel = new UserModel(String.valueOf(false), DatabaseOperationMessage.INCORRECT_PASSWORD.toString());
        } else {
            userModel.setOnline(true);
            userRepository.save(userModel);
            userModel.setSuccess(String.valueOf(true));
            logger.info(String.format(USER_LOGGED_IN, login));
        }
        return userModel;
    }

    @Override
    public void logoutUser(Integer login) {
        UserModel userModel = userRepository.findOne(login);
        userModel.setOnline(false);
        userRepository.save(userModel);
    }

    @Override
    public void saveMessage(Integer sender, Integer receiver, Long timestamp, String message) {
        Message entity = new Message(sender, receiver, timestamp, message);
        messageRepository.save(entity);
    }

    @Override
    public List<Integer> getFriends(Integer id) {
        UserModel userModel = userRepository.findOne(id);
        return userModel.getFriends().stream().map(UserModel::getId).collect(Collectors.toList());
    }

    @Override
    public List<UserModel> getUsersByLetters(String letters) {
        return userRepository.findByLoginStartsWithIgnoreCaseOrNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(letters, letters, letters);
    }

    @Override
    public SearchDTO searchUsers(String requestingId, String letters, String topNumber){
        List<UserModel> users = userRepository.findByLoginStartsWithIgnoreCaseOrNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(letters, letters, letters);
        return new SearchDTO(requestingId, letters, topNumber, users);

    }

}
