package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.DropLocation;
import com.model.PickUpLocation;
import com.model.User;
import com.repository.UserRepository;
import com.response.ResponseSuscessStatus;
import com.service.MapService;

@RestController
public class MapController {
	
	@Autowired
	MapService mapService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/setdroplocation/{ID}")
	public ResponseSuscessStatus setDropLocation(@PathVariable Long ID, @RequestBody DropLocation position) {
		
		User user = mapService.setDropLocation(ID, position);
		
		return new ResponseSuscessStatus(0, "Success", user);
	}
	
	@GetMapping("/getdroplocation/{ID}")
	public ResponseSuscessStatus getDropLocation(@PathVariable Long ID) {
		
		User user = mapService.getDropLocation(ID);
		
		user.setPickupLocation(null);
		
		return new ResponseSuscessStatus(0, "Success", user);
	}
	
	@PostMapping("/setpickuplocation/{ID}")
	public ResponseSuscessStatus setPickupLocation(@PathVariable Long ID, @RequestBody PickUpLocation position) {
		
		User user = mapService.setPickupLocation(ID, position);
		
		return new ResponseSuscessStatus(0, "Success", user);
	}
	
	@GetMapping("/getpickuplocation/{ID}")
	public ResponseSuscessStatus getPickupLocation(@PathVariable Long ID) {
		
		User user = mapService.getPickupLocation(ID);
		
		user.setDropLocation(null);
		return new ResponseSuscessStatus(0, "Success", user);
	}
	
}
