package com.apis.provider;

import java.util.HashMap;
import java.util.Map;

import com.gd.logger.GDLogger;
import com.gd.utils.Utils;
import com.google.gson.JsonObject;

import play.Play;
import play.libs.WS;
import play.modules.guice.InjectSupport;

@InjectSupport
public class FacebookOAuthProvider {
	private GDLogger logger = new GDLogger();
	
    // Configuration properties keys	
	private final String CLIENT_ID = Play.configuration.getProperty("facebook.clientId");
	private final String CLIENT_SECRETE = Play.configuration.getProperty("facebook.clientSecrete");	
	private static final String ACCESS_TOKEN_URL = Play.configuration.getProperty("facebook.accessTokenURL");
	private static final String REDIRECT_URI = Play.configuration.getProperty("facebook.redirectUri");
	private static final String ACCESS_TOKEN = "access_token";
	
	private static final String BASE_URL = "https://graph.facebook.com/";
	private static final String VERSION = "v2.2/"; //Play.configuration.getProperty("facebook.api.version")
	private static final String ME_API = BASE_URL + VERSION + "me?fields=id,first_name,last_name,birthday,gender,email,picture&access_token=%s";
	
	private Map<String, Object> tokenURLParams = new HashMap<String, Object>();
	
	public String retrieveAccessToken(String accessCode) {
		tokenURLParams.put("client_id", CLIENT_ID);
		tokenURLParams.put("client_secret", CLIENT_SECRETE);
		tokenURLParams.put("redirect_uri", REDIRECT_URI);
		tokenURLParams.put("code", accessCode);
		WS.HttpResponse response = WS.url(ACCESS_TOKEN_URL)
				.params(tokenURLParams).post();
		return extractAccessToken(response);
	}
	
	private String extractAccessToken(WS.HttpResponse response) {
		if (response.getStatus() == 200) {
			try {
				Map<String, String> data= response.getQueryString();
				return data.get(ACCESS_TOKEN);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	
	public JsonObject fetchUserProfile(String accessToken) {
		JsonObject me = WS.url(ME_API, accessToken).get().getJson().getAsJsonObject();
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
