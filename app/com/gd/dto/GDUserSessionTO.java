package com.gd.dto;

import models.GDUserSession;

public class GDUserSessionTO {
	private GDUserTO user;
	private String sessionId;
	public GDUserSessionTO(GDUserSession userSession) {
		this.user = new GDUserTO(userSession.getUser());
		this.sessionId = userSession.getSessionId();
	}
	public GDUserTO getUser() {
		return user;
	}
	public void setUser(GDUserTO user) {
		this.user = user;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "GDUserSessionTO [user=" + user + ", sessionId=" + sessionId
				+ "]";
	}
	
}
