package com.project.database.repositories;

import com.project.database.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByLogin(String login);

}