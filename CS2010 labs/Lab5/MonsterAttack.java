public class MonsterAttack {
private int id;
private int day;
private int month;
private int year;
private String name;
private String location;
private int numVictims;

public MonsterAttack(int id,  String name, String location, String date, int num) {
	this.id = id;
	this.name = name;
	this. location = location;
	this.numVictims = num;
	String[] dateData = date.split("/", 0);
	month = Integer.parseInt(dateData[0]);
	day = Integer.parseInt(dateData[1]);
	year = Integer.parseInt(dateData[2]);
	
}
public MonsterAttack() {
	
}

public int getId() {
	return id;
}

public void setId( int id) {
	this.id = id;
}

public int getDay() {
	return day;
}

public void setDay(int day) {
	this.day = day;
}

public int getMonth() {
	return month;
}

public void setMonth(int month) {
	this.month = month;
}

public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public int getNumVictims() {
	return numVictims;
}

public void setNumVictims(int numVictims) {
	this.numVictims = numVictims;
}

public String toString() {
	return "Attack #" + id + " occurred on " + day + "/" + month + "/"  + year +
		 ". " + name + " attacked " + location + ", resulting in " + 
			numVictims + " tragic fatalities."; 

}
}
