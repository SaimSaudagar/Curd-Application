package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.model.User;
import com.model.UserCredentials;
import com.repository.UserRepository;
import com.service.UserService;
import javax.transaction.*;

@Service
@Transactional
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserCredentials passwordChecker(String username, String password){
		
		Boolean check = passwordEncoder.matches(password, userRepository.findByUsername(username).get(0).getPassword());
		
		System.out.println(check);
		
		if(check == true) {
			return new UserCredentials(username, password);
		}
		
		return new UserCredentials("none", "none");
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByID(Long ID) {
		return userRepository.findById(ID);
	}

	@Override
	public void add(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public Optional<User> delete(Long ID) {
		Optional<User> userOpt = userRepository.findById(ID);

		if (userOpt.isPresent()) {
			userRepository.delete(userOpt.get());
			return userOpt;
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> update(User user) {
		Optional<User> userOpt = userRepository.findById(user.getID());

		if (userOpt.isPresent()) {
			User existingUser = userOpt.get();
			
			if(user.getUsername() != null) {
				existingUser.setUsername(user.getUsername());
			}
			
			if(user.getPassword() != null) {
				existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			
			if (user.getFirstName() != null) {
				existingUser.setFirstName(user.getFirstName());
			}

			if (user.getLastName() != null) {
				existingUser.setLastName(user.getLastName());
			}

			if (user.getAge() != null) {
				existingUser.setAge(user.getAge());
			}
			
			if (user.getEmail() != null) {
				if(userRepository.findByEmail(user.getEmail()).isEmpty()){
					existingUser.setEmail(user.getEmail());
				}
			}
			
			if (user.getPhoneNum() != null) {
				if(userRepository.findByPhoneNum(user.getPhoneNum()).isEmpty())
				{
					existingUser.setPhoneNum(user.getPhoneNum());
				}			
			}
			
//			if (user.getCountry() != null) {
//				existingUser.setCountry(user.getCountry());
//			}

			userRepository.save(existingUser);
			
			return Optional.of(existingUser);
		}
		return Optional.empty();
	}
	
	@Override
	public List<User> findByCriteria(String criteria, String searchItem){
		
		switch (criteria) {
		case "username":
			return this.userRepository.findByUsername(searchItem);
		case "firstName":
			return this.userRepository.findByFirstName(searchItem);
		case "lastName":
			return this.userRepository.findByLastName(searchItem);
		case "age":
			try{
				Integer age = Integer.valueOf(searchItem);
				return this.userRepository.findByAge(age);
			}
			catch(NumberFormatException num){
				System.out.println("Cannot convert to number");
			}
			return new ArrayList<>();
		case "country":
			return new ArrayList<>();
		case "email":
			return this.userRepository.findByEmail(searchItem);
		case "phoneNum":
			return this.userRepository.findByPhoneNum(searchItem);
		}
		
		return new ArrayList<>();
	}
}
