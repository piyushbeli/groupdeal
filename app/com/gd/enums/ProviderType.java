package com.gd.enums;

public enum ProviderType {
	GOOGLE("google"),
	FACEBOOK("facebook");
	
	String provider;
	
	private ProviderType(String provider) {
		this.provider = provider;
	}	
	public String getProvider() {
		return this.provider;
	}
}
