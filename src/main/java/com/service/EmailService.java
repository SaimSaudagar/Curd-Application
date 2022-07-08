package com.service;

import org.apache.logging.log4j.Logger;
import java.util.Optional;
import java.util.Random;
import com.model.ContactFormDTO;
import com.model.OTPCheck;
import com.model.User;
import com.repository.UserRepository;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	Logger logger = LogManager.getLogger(EmailService.class);
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String emailTo;
	
	@Async
	public void send(ContactFormDTO contactFormDTO) {
		//prepare email format
		MimeMessagePreparator perparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(contactFormDTO.getEmail()));
				mimeMessage.setSubject(contactFormDTO.getSubject());
				
				long check = userRepository.findByEmail(contactFormDTO.getEmail()).get(0).getID();
				Optional<User> userOpt = userRepository.findById(check);
				User existingUser = userOpt.get();

				String OTP = existingUser.getOTP();
				
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				
				helper.setText(
						"<html>" 
							+	"<body>"
								+ "Rest Password OTP" + "<br/>"
								+	"<br/><br/>"
								+	"Your OTP is : " + OTP
							+	"<body>"
						+"</html>", true);
			}
		};
		
		try {
			emailSender.send(perparator);
			logger.info("Email sent with success");
		}catch (Exception e) {
			logger.error("Error sending mail");
			throw e;
		}
	}
	
	public String OTP() {
		Random r = new Random();
		int OTP = r.nextInt(100000, 999999);
		
		String signal = String.valueOf(OTP);
	}
	
	public String checkOTP(OTPCheck otpCheck) {
		try {
			long check = userRepository.findByEmail(otpCheck.getEmail()).get(0).getID();
			Optional<User> userOpt = userRepository.findById(check);
			User existingUser = userOpt.get();
			String tempOTP = existingUser.getOTP();
			
			if(tempOTP.equals(otpCheck.getOTP())) {
				
				existingUser.setOTP(null);
				userRepository.save(existingUser);
				return "0";	
			}
			return "1";
		}
		catch(Exception e){
			return "1";
		}
	}
}
