package com.example.distrimo.core;

import com.example.distrimo.data.UserData;
import com.example.distrimo.data.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserData createUser(UserRequest userRequest);
    UserData updateUser(UserRequest userRequest);
    UserData getUserByEmail(String email);
    List<UserData> getAllUsers();
    void deleteUser(UUID id);
    boolean userExists(UUID id);
}
