package com.apis.provider;

import play.libs.WS;

import com.gd.logger.GDLogger;
import com.google.gson.JsonObject;

public abstract class AbstractOAuthProvider {
	private GDLogger logger = new GDLogger();
	
	protected abstract String getMeApi();
	
	public JsonObject fetchUserProfile(String accessToken) {
		JsonObject me = WS.url(getMeApi(), accessToken).get().getJson().getAsJsonObject();
		JsonObject error = me.getAsJsonObject(ProviderConstants.ERROR);

        if ( error != null ) {
            final String message = error.get(ProviderConstants.MESSAGE).getAsString();
            final String type = error.get(ProviderConstants.TYPE).getAsString();
            logger.error("Error retrieving profile information from Facebook. Error type: %s, message: %s.", type, message);
            return null;
        } else {
        	return me;
        }
	}
}
