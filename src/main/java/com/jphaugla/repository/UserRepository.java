package com.jphaugla.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jphaugla.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

	List<User> findByFirstNameAndLastName(String firstName, String lastName);

	List<User> findByMiddleNameContains(String firstName);

	List<User> findByRole_RoleName(String roleName);
}
