package com.component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import com.model.City;
//import com.model.Country;
import com.model.User;
import com.repository.UserRepository;

@Component
@javax.transaction.Transactional
public class LoadUserInDB implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.count() > 0) {
			return;
		}

		User user1 = new User("hen", passwordEncoder.encode("hen1234"), "Dr Henrietta", "Keith", 32, "hen@gmail.com",
				"03219231241");
		User user2 = new User("hil", passwordEncoder.encode("hil1234"), "Dr Hilda", "Keogh", 41, "hil@hotmial.com",
				"03002138471");

		List<User> usersList = Arrays.asList(user1, user2);

		usersList.stream().map(user -> {
			user.setPassword(user.getPassword());
			return user;
		}).collect(Collectors.toList());

		userRepository.saveAll(usersList);
	}
}
