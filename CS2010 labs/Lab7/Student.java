package lab7;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
	private int cin;
	private List<Course> courses = new ArrayList<Course>();

	public Student(String name, Address address, int cin) {
		super(name, address);
		this.cin = cin;
	}
	
	public Student() {
		super();
	}

	public int getCin() {
		return cin;
	}
	
	public void setCin(int cin) {
		this.cin = cin;
	}
	
	public List<Course> getCourse() {
		return courses;
	}
	
	public void setCourse(Course course) {
		this.courses.add(course);
	}
	
	public String toString() {
		return "Student Name: " + name + "\nAddress: " + address + "\nCIN: " + cin + "\nCourses Offered: " + courses + "\n";
	}

}
