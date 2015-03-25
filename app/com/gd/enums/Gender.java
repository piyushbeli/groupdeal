package com.gd.enums;

public enum Gender {
	MALE("male"),
	FEMALE("female"),
	UNKNOWN("unknown");
	
	String gender;
	private Gender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return this.gender;
	}
}
