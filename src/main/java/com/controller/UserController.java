package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.model.User;
import com.model.UserCredentials;
import com.repository.UserRepository;
//import com.response.CodeMessageList;
import com.response.ResponseSuscessStatus;
import com.service.UserService;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@PostMapping("/check")
	public UserCredentials passwordChecker(@RequestBody UserCredentials user) {
		return userService.passwordChecker(user.getUsername(), user.getPassword());
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<?> findByID(@PathVariable Long ID) {
		Optional<User> userOpt = userService.findByID(ID);

		if (userOpt.isPresent()) {
			return new ResponseEntity<User>(userOpt.get(), HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}	

	@PostMapping("/add")
	public ResponseSuscessStatus add(@Validated @RequestBody User user) throws Exception {

		if (user.getUsername() == null && user.getPassword() == null) {
			return new ResponseSuscessStatus(1, "Username and password cannot be null", user);
		}

		if (user.getUsername() == null) {
			return new ResponseSuscessStatus(1, "Username cannot be null", user);
		}

		if (user.getPassword() == null) {
			return new ResponseSuscessStatus(1, "Password cannot be null", user);
		}
			
		if (user.getEmail() == null) {
			return new ResponseSuscessStatus(1, "Email cannot be null", user);
		}
		
		if (user.getPhoneNum() == null) {
			return new ResponseSuscessStatus(1, "Phone number cannot be null", user);
		}
		
		if(user.getEmail().contains("@") == false || user.getEmail().contains(".com") == false) {
			return new ResponseSuscessStatus(1, "Email not in correct format", user);
		}
		
		if(user.getPhoneNum().substring(0, 2).compareTo("03") != 0 || user.getPhoneNum().length() != 11) {
			return new ResponseSuscessStatus(1, "Phone number not in correct format", user);
		}		

		List<User> check = userRepository.findByEmail(user.getEmail());
		
		if (check.isEmpty()) {
			
			check = userRepository.findByPhoneNum(user.getPhoneNum());
			
			if (check.isEmpty()) {
				userService.add(user);
				
				return new ResponseSuscessStatus(0, "Entry Added Sucessfully", user);
			}
			else {
				return new ResponseSuscessStatus(1, "Phone number not unique", user);
			}
		}
		else {
			return new ResponseSuscessStatus(1, "Email not unique", user);
		}
		
	}

	@DeleteMapping("/delete/{ID}")
	public ResponseEntity<?> delete(@PathVariable Long ID) {
		Optional<User> userOpt = userService.delete(ID);

		if (userOpt.isPresent()) {
			return new ResponseEntity<String>("User Deleted", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>("User with ID " + ID + " deleted", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update")
	public int update(@RequestBody User user) {

//		Optional<User> userOpt = 
		List<User> temp = userRepository.findByEmail(user.getEmail());
		user.setID(temp.get(0).getID());
		userService.update(user);
		return 0;
//		if (userOpt.isPresent()) {
//			return new ResponseEntity<String>("User updated with ID:" + userOpt.get().getID(), HttpStatus.OK);
//		}
//
//		return new ResponseEntity<String>("User not found with ID:" + user.getID(), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/search")
	public ResponseEntity<?> findByCriteria(@RequestParam(name = "criteria", required = true) String criteria,
			@RequestParam(name = "searchItem", required = true) String searchItem) {
		return new ResponseEntity<List<User>>(userService.findByCriteria(criteria, searchItem), HttpStatus.OK);
	}
}
