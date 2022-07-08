package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.model.ContactFormDTO;
import com.model.OTPCheck;
import com.model.User;
import com.repository.UserRepository;
import com.response.ResponseSuscessStatus;
import com.service.EmailService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendemail")
	public ResponseSuscessStatus sendEmail(@RequestBody ContactFormDTO contactFormDTO){
		
		try {
			String OTP = emailService.OTP();
			long check = userRepository.findByEmail(contactFormDTO.getEmail()).get(0).getID();
			
			Optional<User> userOpt = userRepository.findById(check);
			User existingUser = userOpt.get();
			
			existingUser.setOTP(OTP);
			userRepository.save(existingUser);
			emailService.send(contactFormDTO);
			
			return new ResponseSuscessStatus(0, "Success");

		}
		catch(Exception e){
			return new ResponseSuscessStatus(1, e.getMessage());
		}	
	}
	
	@PostMapping("/checkOTP")
	public ResponseSuscessStatus checkOTP(@RequestBody OTPCheck otpCheck) {
		String check = emailService.checkOTP(otpCheck);
		
		if(check == "0") {
			return new ResponseSuscessStatus(0, "Success");
		}
		
		return new ResponseSuscessStatus(1, "Fail");
	}
}
