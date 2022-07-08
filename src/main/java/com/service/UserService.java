package com.service;

import java.util.List;
import java.util.Optional;
import com.model.User;
import com.model.UserCredentials;

public interface UserService {
	public UserCredentials passwordChecker(String username, String password);
	public List<User> findAll();
	public Optional<User> findByID(Long ID);
	public void add(User user);
	public Optional<User> delete(Long ID);
	public Optional<User> update(User user);
	public List<User> findByCriteria(String criteria, String searchItem);
}
