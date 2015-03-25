package controllers;

import java.util.Map;

import javax.inject.Inject;

import models.GDUserSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.apis.provider.FacebookOAuthProvider;
import com.apis.provider.GoogleOAuthProvider;
import com.gd.dto.GDUserSessionTO;
import com.gd.dto.GDUserTO;
import com.gd.enums.ProviderType;
import com.gd.enums.SessionType;
import com.gd.services.LoginService;
import com.gd.services.UserService;
import com.google.gson.JsonObject;

import play.libs.OAuth2.Response;
import play.mvc.Controller;

public class LoginController extends Controller{
	@Inject
	private static GoogleOAuthProvider googleOAuthProvider;
	@Inject
	private static FacebookOAuthProvider facebookOAuthProvider;
	@Inject
	private static LoginService loginService;
	
	public static void login() {
		Map<String, String> allParams = params.allSimple();
		String userId = params.get("userId");
		System.out.println(allParams + " : " + userId);
	}
	
	public static void loginWithGoogle() {		
		/*String requestBody = params.allSimple().get("body");		
		JSONObject asJson = new JSONObject();
		try {
			asJson = new JSONObject(requestBody);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		String accessCode = asJson.getString("code");
		String accessToken  = googleOAuthProvider.retrieveAccessToken(accessCode);
		GDUserSessionTO userSessionTO = loginWithSocialInfo(accessToken, ProviderType.GOOGLE);
		Map<String, Object> map = new HashedMap();
		map.put("access_token", "ACCESS_TOKEN");
		map.put("userSession", userSessionTO);
		renderJSON(map);*/		
		Map<String, Object> map = new HashedMap();
		map.put("access_token", "ACCESS_TOKEN");
		map.put("userSession", login (ProviderType.GOOGLE));
		renderJSON(map);
	}
	
	public static void loginWithFacebook() {
		/*String requestBody = params.allSimple().get("body");
		JSONObject asJson = new JSONObject();
		try {
			asJson = new JSONObject(requestBody);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		String accessCode = asJson.getString("code");
		String accessToken  = facebookOAuthProvider.retrieveAccessToken(accessCode);
		GDUserSessionTO userSessionTO = loginWithSocialInfo(accessToken, ProviderType.FACEBOOK);
		Map<String, Object> map = new HashedMap();
		map.put("access_token", "ACCESS_TOKEN");
		map.put("userSession", userSessionTO);
		renderJSON(map);*/
		
		Map<String, Object> map = new HashedMap();
		map.put("access_token", "ACCESS_TOKEN");
		map.put("userSession", login(ProviderType.FACEBOOK));
		renderJSON(map);
	}
	
	public static GDUserSessionTO login (ProviderType provider) {
		String requestBody = params.allSimple().get("body");
		JSONObject asJson = new JSONObject();
		String accessToken = null;
		GDUserSessionTO userSessionTO = null;
		try {
			asJson = new JSONObject(requestBody);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		String accessCode = asJson.getString("code");
		switch (provider) {
		case FACEBOOK:
			accessToken  = facebookOAuthProvider.retrieveAccessToken(accessCode);
			userSessionTO = loginWithSocialInfo(accessToken, ProviderType.FACEBOOK);
			break;
		case GOOGLE:
			accessToken  = googleOAuthProvider.retrieveAccessToken(accessCode);
			userSessionTO = loginWithSocialInfo(accessToken, ProviderType.GOOGLE);
			break;
		default:
			break;
		}
		return userSessionTO;		
	}
	
	public static GDUserSessionTO loginWithSocialInfo(String accessToken, ProviderType provider) {
		String loginFrom = params.allSimple().get("sessionType");
		SessionType sessionType = null;
		if (StringUtils.isBlank(loginFrom)) {
			sessionType = SessionType.WEB;
		} else {
			sessionType = SessionType.valueOf(loginFrom.toUpperCase());
		}
		GDUserSessionTO userSessionTO = loginService.login(accessToken, provider, sessionType);
		return userSessionTO;
	}
}
