package com.guice.config;

import com.google.inject.Guice;
import com.google.inject.Injector;

import play.modules.guice.GuiceSupport;

public class ApplicationDependencies extends GuiceSupport{
	@Override
	protected Injector configure() {
		return Guice.createInjector(new GuiceModule());
	}
}
