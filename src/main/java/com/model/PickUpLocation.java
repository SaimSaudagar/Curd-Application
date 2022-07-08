package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "pickuplocation")
public class PickUpLocation {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		@NonNull
		private Long pickup_latitude;
		@NonNull
		private Long pickup_longitude;
		
		public PickUpLocation() {
		}

		public PickUpLocation(long pickup_latitude, long pickup_longitude) {
			this. pickup_latitude = pickup_latitude;
			this.pickup_longitude = pickup_longitude;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getLatitude() {
			return pickup_latitude;
		}

		public void setLatitude(Long pickup_latitude) {
			this.pickup_latitude = pickup_latitude;
		}

		public Long getLongitude() {
			return pickup_longitude;
		}

		public void setLongitude(Long pickup_longitude) {
			this.pickup_longitude = pickup_longitude;
		}
}
