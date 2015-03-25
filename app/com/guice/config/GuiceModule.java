package com.guice.config;

import com.apis.provider.GoogleOAuthProvider;
import com.gd.services.UserService;
import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule{
	
	@Override	
	protected void configure() {
		this.bind(GoogleOAuthProvider.class).asEagerSingleton();
		this.bind(UserService.class).asEagerSingleton();
	}

}
