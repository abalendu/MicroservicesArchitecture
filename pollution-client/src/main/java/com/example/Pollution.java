package com.example;

public class Pollution {

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
