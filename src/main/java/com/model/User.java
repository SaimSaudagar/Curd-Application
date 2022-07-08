package com.model;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	@NonNull
	private String username;
	@NonNull
	private String password;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private Integer age;
	@NonNull
	private String OTP;
//	@NonNull
//	@ManyToOne(targetEntity = Country.class, cascade = {CascadeType.ALL})
//	private Country country;
//	@NonNull
//	@ManyToOne(targetEntity = City.class, cascade = {CascadeType.ALL})
//	private City city;
	@NonNull
	private String email;
	@NonNull
	private String phoneNum; 
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	List<DropLocation> dropLocation = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	List<PickUpLocation> pickupLocation = new ArrayList<>();
	
	public User() {
		super();
	}

	public User(String username, String password, String firstName, String lastName, Integer age,
			String email, String phoneNum) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.phoneNum = phoneNum;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public List<DropLocation> getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(List<DropLocation> dropLocation) {
		this.dropLocation = dropLocation;
	}

	public List<PickUpLocation> getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(List<PickUpLocation> pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	
	
}