package lab7;

import java.util.ArrayList;
import java.util.List;

public class FacultyMember extends Person {
	private int empId;
	private List<Course>courses = new ArrayList<Course>();

	public FacultyMember(String name, Address address, int empId) {
		super(name, address);
		this.empId = empId;
	}
	
	public FacultyMember() {
		super();
	}
	
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public List<Course> getCourse() {
		return courses;
	}
	
	public void setCourse(Course course) {
		this.courses.add(course);
	}
	
	public String toString() {
		return "FacultyMember name: " + name + "\nAddress: " + address + "\nEmployeeID: " + empId + "\nCourses taught: " + courses + "\n";
	}
}
