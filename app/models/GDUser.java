package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.apis.provider.SocialUser;
import com.gd.enums.Gender;

import play.db.jpa.Model;

@Entity
public class GDUser extends Model{
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column (name="picture_url")
	private String pictureUrl;
	@Column(name="gender")
	private Gender gender;
	@Column(name="email")
	private String email;
	@Column(name="last_accessed")
	public Date lastAccessed;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	private List<GDUserIdentity> userIdentities;
	
	public GDUser(SocialUser socialUser) {
		this.firstName = socialUser.getFirstName();
		this.lastName = socialUser.getLastName();
		this.email = socialUser.getEmail();
		this.gender = socialUser.getGender();
		this.pictureUrl = socialUser.getPictureUrl();
	}	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	public List<GDUserIdentity> getUserIdentities() {
		return userIdentities;
	}
	public void setUserIdentities(List<GDUserIdentity> userIdentities) {
		this.userIdentities = userIdentities;
	}
	
	@Override
	public boolean equals(Object other) {		
		if (other instanceof GDUser) {
			other = (GDUser)other;
			return ((GDUser) other).getId().equals(this.getId());
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "GDUser [firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}	
}
