package com.project.database.repositories;

import com.project.database.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll();

    User findOne(String login);


    //TODO: solve unchecked assignment warning
    User save(User user);
}