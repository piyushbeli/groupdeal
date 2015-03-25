package com.gd.services;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import models.GDUser;
import models.GDUserIdentity;
import play.modules.guice.InjectSupport;

import com.apis.provider.SocialUser;
import com.gd.dao.UserDAO;
import com.gd.dao.UserIdentityDAO;
import com.gd.dto.GDUserTO;
import com.gd.enums.ProviderType;

@InjectSupport
public class UserService {
	@Inject
	private UserDAO userDAO;
	@Inject
	private UserIdentityDAO userIdentityDAO;
	@Inject
	private SocialUserService socialUserService;
	
	public GDUser findUser(Long userId) {		
		return userDAO.findById(userId);		
	}
	
	public GDUser findUser(String accessToken, ProviderType provider) {
		return userIdentityDAO.findUserWithIdentity(accessToken, provider);
	}
	
	/**
	 * 
	 * @param user
	 * @param accessToken
	 * @param providerType
	 * @return true if user identity matches one of the identities associated with the user. It may happen that a user is trying to attack by changing the
	 * userId in local/session storage. We can do 2 way authentication to provide such attack.
	 */
	public boolean isValidUser(GDUser user, String providerUserId, ProviderType provider) {
		if (StringUtils.isEmpty(providerUserId) || provider == null) {
			return false;
		}
		List<GDUserIdentity> userIdentities = user.getUserIdentities();
		GDUserIdentity userIdentity = new GDUserIdentity(user, providerUserId, provider);
		return userIdentities.contains(userIdentity);
	}
	
	public boolean isValidUser(Long userId, String providerUserId, ProviderType provider) {
		GDUser user = userDAO.findById(userId);
		return isValidUser(user, providerUserId, provider);
	}
	
	public GDUser signup (SocialUser socialUser) {
		GDUser user = new GDUser(socialUser);
		GDUserIdentity userIdentity = new GDUserIdentity(user, socialUser.getProviderUserId(), socialUser.getProvider());
		boolean success = userDAO.create(user) && userIdentityDAO.create(userIdentity);		
		if (success) {
			return user;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param accessToken
	 * @param provider
	 * @return GDUser for the social profile. If a user exist with that social profile then that is return else a new user is created
	 * with that social profile and that brand new user is return.
	 */
	public GDUser fetchUser (String accessToken, ProviderType provider) {
		SocialUser socialUser = socialUserService.fetchSocialProfile(accessToken, provider);
		GDUser user = userForSocialProfile(socialUser);
		if (user == null) {
			user = signup(socialUser);
		}
		return user;		
	}
	
	private GDUser userForSocialProfile (SocialUser socialUser) {
		GDUser user = userIdentityDAO.findUserWithIdentity(socialUser.getProviderUserId(), socialUser.getProvider());
		return user;
	}
}
