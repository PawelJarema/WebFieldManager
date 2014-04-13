package web.field.model;

public class LogOnAnswer {
	private String token;
	private String firstname;
	private String lastname;
	private boolean passwordchanged;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean getPasswordchanged() {
		return passwordchanged;
	}

	public void setPasswordchanged(boolean passwordchanged) {
		this.passwordchanged = passwordchanged;
	}
}
