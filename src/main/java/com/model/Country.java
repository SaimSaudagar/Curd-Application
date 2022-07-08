//package com.model;
//
//import org.springframework.lang.NonNull;
//import javax.persistence.*;
//
//@Entity
//@Table(name = "country")
//public class Country {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long ID;
//	@NonNull
//	private String Country;
//	
//	@Override
//	public String toString() {
//		return  Country;
//	}
//
//	public Country() {
//		super();
//	}
//	
//	public Country(String country) {
//		super();
//		Country = country;
//	}
//	
//	public void setID(Long iD) {
//		ID = iD;
//	}
//	
//	public void setCountry(String country) {
//		Country = country;
//	}
//	
//	public Long getID() {
//		return ID;
//	}
//
//	public String getCountry() {
//		return Country;
//	}
//
//}
