package com.gd.enums;

public enum Mode {
	DEV("dev"),
	SANDBOX("sandbox"),
	QA("qa");
	
	private String mode;
	
	Mode(String mode) {
		this.mode = mode;
	}	
	public String getMode() {
		return this.mode;
	}
}
