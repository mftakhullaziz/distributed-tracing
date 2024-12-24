package com.example.distrimo.core;

import com.example.distrimo.annotation.Core;
import com.example.distrimo.data.UserData;
import com.example.distrimo.data.UserRequest;
import com.example.distrimo.infra.UserJPAGateway;
import com.example.distrimo.infra.UserTRecord;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Log4j2
@Observed
@Core
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJPAGateway userGateway;

    @Override
    public UserData createUser(UserRequest userRequest) {
        UserTRecord userTRecord = userGateway.saveOrUpdate(
            buildUserTRecord(userRequest)
        );
        return buildUserData(userTRecord);
    }

    @Override
    public UserData updateUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserData getUserByEmail(String email) {
        log.info("Getting user by email: {}", email);
        return userGateway.findUserByEmail(email)
            .map(this::buildUserData)
            .orElse(null);
    }

    @Override
    public List<UserData> getAllUsers() {
        return userGateway.allUsers()
            .stream()
            .map(this::buildUserData)
            .toList();
    }

    @Override
    public void deleteUser(UUID id) {
        userGateway.deleteById(id);
    }

    @Override
    public boolean userExists(UUID id) {
        return userGateway.userExists(id);
    }

    private static String generateUniqueUsername(String firstName, String lastName) {
        String normalizedFirstName = firstName.trim().toLowerCase().replaceAll("\\s+", "");
        String normalizedLastName = lastName.trim().toLowerCase().replaceAll("\\s+", "");
        int randomNumber = new Random().nextInt(9000) + 1000;
        return normalizedFirstName + normalizedLastName + randomNumber;
    }

    private UserTRecord buildUserTRecord(UserRequest userRequest) {
        return UserTRecord.builder()
            .email(userRequest.getEmail())
            .username(generateUniqueUsername(userRequest.getFirstName(), userRequest.getLastName()))
            .firstName(userRequest.getFirstName())
            .lastName(userRequest.getLastName())
            .password(userRequest.getPassword())
            .build();
    }

    private UserData buildUserData(UserTRecord userTRecord) {
        return UserData.builder()
            .id(userTRecord.getId())
            .email(userTRecord.getEmail())
            .username(userTRecord.getUsername())
            .firstName(userTRecord.getFirstName())
            .lastName(userTRecord.getLastName())
            .password(userTRecord.getPassword())
            .isActive(userTRecord.isActive())
            .createdAt(userTRecord.getCreatedAt())
            .updatedAt(userTRecord.getUpdatedAt())
            .build();
    }
}
