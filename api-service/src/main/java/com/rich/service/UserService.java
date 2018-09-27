package com.rich.service;

import com.rich.entity.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	public List<User> getAll() {
		return Arrays.asList(new User("rich", 30), new User("maple", 30));
	}

	public Optional<User> getById(String id) {
		return Optional.of(new User("rich", 30));
	}

	public String create(User user) {
		//TODO: persist user
		return UUID.randomUUID().toString();
	}
}
