package com.project.database.interfaces;

import com.project.database.model.User;

public interface AccessService {
    User registerUser(String login, String name, String lastName, String password, String avatar, boolean online);

    User loginUser(String login, String password);
}
