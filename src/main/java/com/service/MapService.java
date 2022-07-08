package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.DropLocation;
import com.model.PickUpLocation;
import com.model.User;
import com.repository.UserRepository;

@Service
@Transactional
public class MapService {

	@Autowired
	UserRepository userRepository;
	
	@SuppressWarnings("deprecation")
	public User setDropLocation(Long ID, DropLocation position) { 
		userRepository.getById(ID).getDropLocation().add(position);
		User user = userRepository.getById(ID);
		
		this.userRepository.save(user);
		
		return user;
	}
	
	@SuppressWarnings("deprecation")
	public User getDropLocation(Long ID) {
		
		return userRepository.getById(ID);
	}
	
	@SuppressWarnings("deprecation")
	public User setPickupLocation(Long ID, PickUpLocation position) { 
		userRepository.getById(ID).getPickupLocation().add(position);
		User user = userRepository.getById(ID);
		
		this.userRepository.save(user);
		
		return user;
	}
	
	@SuppressWarnings("deprecation")
	public User getPickupLocation(Long ID) {
		
		return userRepository.getById(ID);
	}
}
