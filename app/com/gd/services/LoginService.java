package com.gd.services;

import java.util.UUID;

import javax.inject.Inject;

import com.gd.dto.GDUserSessionTO;
import com.gd.dto.GDUserTO;
import com.gd.enums.ProviderType;
import com.gd.enums.SessionType;

import models.GDUser;
import models.GDUserSession;

import play.modules.guice.InjectSupport;

@InjectSupport
public class LoginService {
	@Inject
	private static UserService userService;
	
	public String generateSessionId() {
		return UUID.randomUUID().toString();
	}
	
	public GDUserSession createUserSession(GDUser user, SessionType sessionType) {
		GDUserSession session = new GDUserSession(user, generateSessionId(), sessionType);
		session.create();
		return session;
	}
	
	public GDUserSessionTO login(String accessToken, ProviderType provider, SessionType sessionType) {
		GDUser user = userService.fetchUser(accessToken, provider);
		GDUserSession session = createUserSession(user, sessionType);
		GDUserSessionTO userSessionTO = new GDUserSessionTO(session);
		return userSessionTO;
	}
}
