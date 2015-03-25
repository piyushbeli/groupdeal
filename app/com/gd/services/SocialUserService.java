package com.gd.services;

import javax.inject.Inject;

import play.modules.guice.InjectSupport;

import com.apis.provider.FacebookOAuthProvider;
import com.apis.provider.GoogleOAuthProvider;
import com.apis.provider.SocialUser;
import com.gd.enums.Gender;
import com.gd.enums.ProviderType;
import com.google.gson.JsonObject;

@InjectSupport
public class SocialUserService {
	@Inject
	private GoogleOAuthProvider googleProvider;
	@Inject
	private FacebookOAuthProvider facebookProvider;
	
	//Field to be fetched from google profile
    private static final String ID = "id";
    private static final String FIRST_NAME = "given_name";
    private static final String LAST_NAME = "family_name";
    private static final String FB_FIRST_NAME = "first_name";
    private static final String FB_LAST_NAME = "last_name";
    private static final String PICTURE = "picture";
    private static final String EMAIL = "email";
    private static final String GENDER = "gender";
	
	public SocialUser fetchSocialProfile (String accessToken, ProviderType provider) {
		switch (provider) {
			case GOOGLE:
				return fetchGoogleProfile(accessToken);
			case FACEBOOK:
				return fetchFacebookProfile(accessToken);	
			default:
				return null;
		}		
	}
	
	private SocialUser fetchGoogleProfile (String accessToken) {
		SocialUser socialUser = new SocialUser();
		JsonObject me = googleProvider.fetchUserProfile(accessToken);
		socialUser.setProviderUserId(me.get(ID).getAsString());
		socialUser.setProvider(ProviderType.FACEBOOK);
    	socialUser.setFirstName(me.get(FIRST_NAME).getAsString());
    	socialUser.setLastName(me.get(LAST_NAME).getAsString());
    	socialUser.setGender(Gender.valueOf(me.get(GENDER).getAsString().toUpperCase()));
    	socialUser.setEmail(me.get(EMAIL).getAsString());
    	socialUser.setPictureUrl(me.get(PICTURE).getAsString());
		return socialUser;
	}
	
	private SocialUser fetchFacebookProfile (String accessToken) {
		SocialUser socialUser = new SocialUser();
		JsonObject me = facebookProvider.fetchUserProfile(accessToken);
		socialUser.setProviderUserId(me.get(ID).getAsString());
		socialUser.setProvider(ProviderType.FACEBOOK);
    	socialUser.setFirstName(me.get(FB_FIRST_NAME).getAsString());
    	socialUser.setLastName(me.get(FB_LAST_NAME).getAsString());
    	socialUser.setGender(Gender.valueOf(me.get(GENDER).getAsString().toUpperCase()));
    	socialUser.setEmail(me.get(EMAIL).getAsString());
    	if (me.get(PICTURE) != null) {
    		socialUser.setPictureUrl(me.get(PICTURE).getAsJsonObject().get("data").getAsJsonObject().get("url").getAsString());
    	}
		return socialUser;
	}
	
}
