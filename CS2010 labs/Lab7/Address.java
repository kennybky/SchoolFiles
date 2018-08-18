package lab7;

public class Address {
	int streetNum;
	String streetName;
	String city;
	String state;
	String country;

	public Address(int streetNum, String streetName, String city, String state,
			String country) {
		this.streetNum = streetNum;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	public int getStreetNum() {
		return streetNum;
	}
	
	public void setStreetNum(int streetNum) {
		this.streetNum = streetNum;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName =streetName ;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString() {
		return streetNum + " " + streetName + " " + city + " " + " " + state + "  " + country;
	}
	

}
