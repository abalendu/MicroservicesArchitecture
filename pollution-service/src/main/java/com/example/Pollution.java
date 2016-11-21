package com.example;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pollution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id; // id

	private String city; // city_name

	private long pollutionLevel; // pollutionLevel

	public Long getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPollutionLevel() {
		return pollutionLevel;
	}

	public void setPollutionLevel(long pollutionLevel) {
		this.pollutionLevel = pollutionLevel;
	}

	@Override
	public String toString() {
		return "PollutionData [id=" + id + ", city=" + city + ", pollutionLevel=" + pollutionLevel + "]";
	}

	Pollution() {
	}

	public Pollution(String city, long pollutionLevel) {
		this.city = city;
		this.pollutionLevel = pollutionLevel;
	}

}
