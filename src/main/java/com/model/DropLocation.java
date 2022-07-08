package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "droplocation")
public class DropLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	private Long drop_latitude;
	@NonNull
	private Long drop_longitude;
	
	public DropLocation() {
	}

	public DropLocation(long drop_latitude, long drop_longitude) {
		this.drop_latitude = drop_latitude;
		this.drop_longitude = drop_longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLatitude() {
		return drop_latitude;
	}

	public void setLatitude(Long drop_latitude) {
		this.drop_latitude = drop_latitude;
	}

	public Long getLongitude() {
		return drop_longitude;
	}

	public void setLongitude(Long drop_longitude) {
		this.drop_longitude = drop_longitude;
	}
}