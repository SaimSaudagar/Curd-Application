package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByUsername(String username);
	List<User> findByFirstName(String firstName);
	List<User> findByLastName(String lastName);
	List<User> findByAge(Integer age);
//	List<User> findByCountry(String country);
	List<User> findByEmail(String email);
	List<User> findByPhoneNum(String phoneNum);
	Boolean findByOTP(String oTP);
}
