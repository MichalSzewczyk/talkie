package com.talkie.database.interfaces;

import com.talkie.database.model.FriendRelation;
import com.talkie.database.model.UserModel;
import com.talkie.graphql.model.SearchDTO;

import java.util.List;

public interface AccessService {
    UserModel registerUser(String login, String name, String lastName, String password, String avatar, boolean online);

    UserModel loginUser(String login, String password);

    void logoutUser(Integer id);

    void saveMessage(Integer sender, Integer receiver, Long timestamp, String message);

    List<Integer> getFriends(Integer id);

    List<UserModel> getUsersByLetters (String letters);

    SearchDTO searchUsers(String requestingId, String letters, String topNumber);

    FriendRelation makeFriends(String who, String with);

    boolean removeFriends(String who, String with);

}
