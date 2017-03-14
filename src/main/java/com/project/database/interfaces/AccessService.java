package com.project.database.interfaces;

import com.project.database.model.User;

public interface AccessService {
    User registerUser(User user);

    User loginUser(User user);
}
