package com.apis.provider;

import com.gd.enums.Gender;
import com.gd.enums.ProviderType;

public class SocialUser {
	private String firstName;
	private String lastName;
	private String email;
	private Gender gender;
	private String providerUserId;
	private String pictureUrl;
	private ProviderType provider;
	
	public ProviderType getProvider() {
		return provider;
	}
	public void setProvider(ProviderType provider) {
		this.provider = provider;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getProviderUserId() {
		return providerUserId;
	}
	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}	
}
