package com.project.database.impl;

import com.project.database.interfaces.AccessService;
import com.project.database.model.User;
import com.project.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DatabaseAccessFacade implements AccessService {
    private final UserRepository userRepository;

    @Autowired
    public DatabaseAccessFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
//        if (userRepository.findOne(user.getLogin()) != null)
//            user.setSuccess(false);
//        else {
//            userRepository.save(user);
//        }
        return user;
    }

    public User loginUser(User user) {
//        if (!userRepository.exists(user.getLogin()))
//            user.setSuccess(false);
        return user;
    }

}
