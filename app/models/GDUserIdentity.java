package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

import com.gd.enums.ProviderType;

@Entity
public class GDUserIdentity extends Model{
	@JoinColumn(name = "user_id")
	@ManyToOne (targetEntity = GDUser.class)
	private GDUser user;
	@Column(name = "access_token")
	private String accessToken;
	@Column(name = "expiry_date")
	private Date expiryDate;
	@Column(name = "provider_type")
	@Enumerated(EnumType.STRING)
	private ProviderType provider;
	@Column(name = "refresh_token")
	private String refreshToken;
	@Column (name = "email")
	private String email;
	@Column (name="provider_user_id")
	private String providerUserId;
	
	public GDUserIdentity() {
		
	}
	
	public GDUserIdentity(GDUser user, String providerUserId, ProviderType provider) {
		this.user = user;
		this.providerUserId = providerUserId;
		this.provider = provider;
	}
	
	public GDUser getUser() {
		return user;
	}
	public void setUser(GDUser user) {
		this.user = user;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public ProviderType getProvider() {
		return provider;
	}
	public void setProvider(ProviderType provider) {
		this.provider = provider;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GDUserIdentity) {
			obj = (GDUserIdentity) obj;
			return ((GDUserIdentity) obj).getProviderUserId().equals(
					this.providerUserId)
					&& ((GDUserIdentity) obj).getProvider().equals(
							this.provider)
					&& ((GDUserIdentity) obj).getUser().equals(
							this.getUser());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "GDUserIdentity [user=" + user + ", expiryDate=" + expiryDate
				+ ", provider=" + provider + ", email=" + email
				+ ", providerUserId=" + providerUserId + "]";
	}	
}
