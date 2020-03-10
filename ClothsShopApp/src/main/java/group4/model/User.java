package group4.model;

//This model describes the user

public class User {
	private String email;
	private String userName;
	private String password;
	private int admin;
 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(String stringInput) {
		this.admin = Integer.parseInt(stringInput);
	}

	public boolean isAdmin() 
	{
		if(admin == 1)
		{return true;}
		return false;
	}
}
