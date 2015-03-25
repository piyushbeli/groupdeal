package com.gd.utils;

import com.gd.enums.Mode;

public class Utils {
	public static String getRedirectUri() {
		if (Constants.mode.toUpperCase().equals(Mode.DEV.toString())) {
			return "http://localhost:9000/";
		}
		return null;
	}
}
