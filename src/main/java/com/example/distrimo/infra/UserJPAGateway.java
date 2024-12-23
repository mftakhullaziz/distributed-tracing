package com.example.distrimo.infra;

import com.example.distrimo.annotation.Gateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Gateway
@Component
public class UserJPAGateway {

    private final UserJPARepository userJPARepository;

    public UserJPAGateway(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    public UserTRecord saveOrUpdate(UserTRecord user) {
        return userJPARepository.save(user);
    }

    public Optional<UserTRecord> findUserById(UUID id) {
        return userJPARepository.findById(id);
    }

    public Optional<UserTRecord> findUserByEmail(String email) {
        return userJPARepository.findByEmail(email);
    }

    public List<UserTRecord> allUsers() {
        return userJPARepository.findAll();
    }

    public void deleteById(UUID id) {
//        userJPARepository.findById(id)
//            .ifPresent(user -> {
//                user.setActive(false);
//                saveOrUpdate(user);
//            });
    }

    public boolean userExists(UUID id) {
        return userJPARepository.existsById(id);
    }
}
