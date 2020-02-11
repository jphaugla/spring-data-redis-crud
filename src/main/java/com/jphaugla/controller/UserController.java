package com.jphaugla.controller;

import java.util.List;
import java.util.Optional;

import com.jphaugla.domain.User;
import com.jphaugla.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/save_user")
	public String saveUser() {
		// Role role = new Role("1", "CEO");
		User user = new User("1","Jason","Paul","Haugland",
				"CEO");
		userRepository.save(user);
		user = new User("2","Jason","Robert","Smith",
				"CEO");
		userRepository.save(user);
		return "Done";
	}

	@GetMapping("/get_user_first_last")
	public List<User> getUser(@RequestParam String firstName, @RequestParam String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping("/get_user_id")
	public Optional<User> getUser(@RequestParam String id) {
		return userRepository.findById(id);
	}

	@PutMapping("/put_user")
	public String putUser(@RequestBody User user) {
		userRepository.save(user);
		return "Done";
	}

	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam String id) {
		userRepository.deleteById(id);
		return "Done";
	}
}
