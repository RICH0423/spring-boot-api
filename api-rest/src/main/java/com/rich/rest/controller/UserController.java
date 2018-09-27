package com.rich.rest.controller;

import com.rich.entity.User;
import com.rich.rest.exception.DataNotFoundException;
import com.rich.rest.utils.Constants;
import com.rich.service.UserService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author rich
 *
 */
@Api(value = "User API", tags = { "User" })
@RestController
@RequestMapping(Constants.API_VERSION_1 + Constants.USER_ENTRY)
public class UserController {

    private final UserService userService;
    private final MeterRegistry meterRegistry;

    public UserController(UserService userService, MeterRegistry meterRegistry) {
        this.userService = userService;
        this.meterRegistry = meterRegistry;
    }

    @ApiOperation(value = "Retrieve list of users")
    @GetMapping
    public List<User> getAllUsers() {
        List<User> user = userService.getAll();
        meterRegistry.gaugeCollectionSize("database.size", Tags.of("user", "size"), user);
        return user;
    }

    @ApiOperation(value = "Retrieve user by ID", response = User.class)
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        Optional<User> userOptional = userService.getById(id);
        if(!userOptional.isPresent()) {
            throw new DataNotFoundException("User not found, id: " + id);
        }

        return userOptional.get();
    }

    @ApiOperation(value = "Create an user")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String createdId = userService.create(user);
        meterRegistry.counter("api-users-created").increment();
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

}
