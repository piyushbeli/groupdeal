package com.gd.dao;

import com.gd.enums.ProviderType;

import models.GDUser;
import play.modules.guice.InjectSupport;

@InjectSupport
public class UserDAO {
	public GDUser findById(Long id) {
		return GDUser.findById(id);
	}
	
	public boolean create(GDUser user) {
		return user.create();
	}
}
