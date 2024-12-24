package com.example.distrimo.controller;

import com.example.distrimo.core.UserService;
import com.example.distrimo.data.UserData;
import com.example.distrimo.data.UserRequest;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log4j2
@Observed
@RestController
@RequestMapping("api/user")
@Tag(name = "User Rest API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserData> createUser(@RequestBody UserRequest userRequest) {
        UserData userData = userService.createUser(userRequest);
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserData> getUserByEmail(@PathVariable String email) {
        log.info("Call controller");
        UserData userData = userService.getUserByEmail(email);
        return ResponseEntity.ok(userData);
    }

    @GetMapping
    public ResponseEntity<List<UserData>> getAllUsers() {
        List<UserData> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> userExists(@PathVariable UUID id) {
        boolean exists = userService.userExists(id);
        return ResponseEntity.ok(exists);
    }

}
