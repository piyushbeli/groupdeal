package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.gd.enums.SessionType;

import play.db.jpa.Model;

@Entity
public class GDUserSession extends Model{
	@Column(name="sessionId")
	private String sessionId;
	@ManyToOne(targetEntity= GDUser.class, fetch=FetchType.EAGER)
	private GDUser user;
	@Enumerated(value=EnumType.STRING)
	private SessionType sessionType;
	
	public GDUserSession(GDUser user, String sessionId, SessionType sessionType) {
		this.user = user;
		this.sessionId = sessionId;
		this.sessionType = sessionType;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public SessionType getSessionType() {
		return sessionType;
	}
	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}
	public GDUser getUser() {
		return user;
	}
	public void setUser(GDUser user) {
		this.user = user;
	}	
	@Override
	public String toString() {
		return "GDUserSession [sessionId=" + sessionId + ", user=" + user
				+ ", sessionType=" + sessionType + "]";
	}
}
