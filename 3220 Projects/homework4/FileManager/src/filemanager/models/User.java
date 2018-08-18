package filemanager.models;



public class User {
	String userId;
	String password;
	String fname;
	String lname;
	String name;
	
	
	
	


	public User(String userId, String password, String fname, String lname) {
		this.userId = userId;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.name = fname + " " + lname;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
