package com.gd.utils;

import play.Play;

public class Constants {
	public static final String mode = Play.configuration.getProperty("application.mode");
	public static final String baseUri = "groupdeal.com";
}
