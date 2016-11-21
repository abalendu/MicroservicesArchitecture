package com.example;


import java.io.Serializable;

public class Pollution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String city; // city_name

	private long pollutionLevel; // pollutionLevel

	public String getCity() {
		return city;
	}

	public long getPollutionLevel() {
		return pollutionLevel;
	}

	public String getPollutedCities() {
		return getCity() + ":" + getPollutionLevel();
	}
}
