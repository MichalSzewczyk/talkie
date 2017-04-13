package com.talkie.utils;

import com.talkie.database.model.UserModel;
import com.talkie.dialect.messages.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<User> convertDatabaseUsersToUsers(List<UserModel> userList) {
        return userList.stream().map(Utils::convertUserModelToUser).collect(Collectors.toList());
    }

    public static List<UserModel> convertUsersToUserModel(List<User> userList) {
        return userList.stream().map(UserModel::new).collect(Collectors.toList());
    }

    public static User convertUserModelToUser(UserModel user) {
        User converted = new User();
        converted.setLogin(user.getLogin());
        converted.setName(user.getName());
        converted.setLastName(user.getLastName());
        converted.setPassword(user.getPassword());
        converted.setAvatar(user.getAvatar());
        converted.setOnline(user.getOnline());
        converted.setFriends(Utils.convertDatabaseUsersToUsers(user.getFriends()));
        converted.setSuccess(user.getSuccess());
        converted.setMessage(user.getMessage());
        return converted;
    }

}
