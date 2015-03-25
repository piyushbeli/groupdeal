package com.apis.provider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.json.JSONObject;

import play.Play;
import play.libs.OAuth2;
import play.libs.OAuth2.Response;
import play.libs.WS;
import play.modules.guice.InjectSupport;

import com.gd.logger.GDLogger;
import com.gd.utils.Utils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@InjectSupport
public class GoogleOAuthProvider {
    // Configuration properties keys	
	private final String CLIENT_ID = Play.configuration.getProperty("google.clientId");
	private final String CLIENT_SECRETE = Play.configuration.getProperty("google.clientSecrete");	
	private final String GRANT_TYPE = Play.configuration.getProperty("google.grantType");
	private static final String ACCESS_TOKEN_URL = Play.configuration.getProperty("google.accessTokenURL");
	private static final String REDIRECT_URI = Play.configuration.getProperty("google.redirectUri");
	
	//Field constants
	private static final String PROFILE_API_URL = "https://www.googleapis.com/userinfo/v2/me";
	private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String TYPE = "type";
    
    private static final String BEARER = "Bearer";
  
	
	private Map<String, Object> tokenURLParams = new HashMap<String, Object>();
	private GDLogger logger = new GDLogger();

	public String retrieveAccessToken(String accessCode) {
		tokenURLParams.put("client_id", CLIENT_ID);
		tokenURLParams.put("client_secret", CLIENT_SECRETE);
		tokenURLParams.put("redirect_uri", REDIRECT_URI);
		tokenURLParams.put("code", accessCode);
		tokenURLParams.put("grant_type", GRANT_TYPE);
		WS.HttpResponse response = WS.url(ACCESS_TOKEN_URL)
				.params(tokenURLParams).post();
		return extractAccessToken(response);
	}
	 
	private String extractAccessToken(WS.HttpResponse response) {
		if (response.getStatus() == 200) {
			JsonObject asJson = (JsonObject)response.getJson();
			return asJson.get("access_token").getAsString();
		} else {
			return null;
		}
	}
	
	public JsonObject fetchUserProfile (String accessToken) {
		JsonObject me = WS.url(PROFILE_API_URL).setHeader("Authorization", getAuthorizationHeader(accessToken)).get().getJson().getAsJsonObject();
		JsonObject error = me.getAsJsonObject(ERROR);

        if (error != null) {
            final String message = error.get(MESSAGE).getAsString();
            final String type = error.get(TYPE).getAsString();
            logger.error("Error retrieving profile information from Google. Error type: %s, message: %s.", type, message);
            //TODO Throw an exception
            return null;
        } else {        
        	return me;
        }
	}
	
	private String getAuthorizationHeader(String accessToken) {
		return BEARER + " " + accessToken;
	}
	
}
