package com.talkie.database.interfaces;

import com.talkie.database.model.User;

import java.util.List;

public interface AccessService {
    User registerUser(String login, String name, String lastName, String password, String avatar, boolean online);

    User loginUser(String login, String password);

    void logoutUser(Integer id);

    void saveMessage(Integer sender, Integer receiver, Long timestamp, String message);

    List<Integer> getFriends(Integer id);

    List<User> getUsersByLetters (String letters);

}
