package my.test.apps.shared.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id private String emailAddress;
	private String googleAccountId;
	private String service;

	protected MyUser() {}
	
	public MyUser(String email) {
		this.emailAddress = email;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getGoogleAccountId() {
		return googleAccountId;
	}
	public void setGoogleAccountId(String googleAccountId) {
		this.googleAccountId = googleAccountId;
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
}
