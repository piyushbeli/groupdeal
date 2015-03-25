package com.gd.enums;

public enum SessionType {
	WEB("web"),
	IOS("ios"),
	ANDROID("android");
	
	String type;
	SessionType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
