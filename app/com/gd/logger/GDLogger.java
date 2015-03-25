package com.gd.logger;

import models.GDUser;
import play.Logger;

public class GDLogger {
	private GDUser user;
	public GDLogger() {
		
	}
	
	public GDLogger(GDUser user) {
		this.user = user;
	}
	
	public void info(String message, Object... args) {
		message = getPrefix() + message;
		Logger.info(message, args);
	}
	
	public void error(String message, Object... args) {
		message = getPrefix() + message;
		Logger.error(message, args);
	}
	
	private String getPrefix() {
		if (this.user != null) {
			return this.user.toString();
		} else {
			return "";
		}		
	}
	
}
