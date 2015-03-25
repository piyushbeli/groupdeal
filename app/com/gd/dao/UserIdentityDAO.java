package com.gd.dao;

import java.util.List;

import com.gd.enums.ProviderType;

import models.GDUser;
import models.GDUserIdentity;

public class UserIdentityDAO {
	public GDUser findUserWithIdentity(String providerUserId, ProviderType provider) {
		//TODO should I use .first() ??
		GDUserIdentity userIdentity = GDUserIdentity.find("providerUserId = ? and provider = ?", providerUserId, provider).first();
		if (userIdentity != null) {
			return userIdentity.getUser();
		} else {
			return null;
		}
	}
	
	public boolean create(GDUserIdentity userIdentity) {
		return userIdentity.create();
	}
}
