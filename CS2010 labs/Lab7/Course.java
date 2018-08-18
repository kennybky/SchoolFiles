package lab7;

public class Course {
	private String courseId;
	private String courseName;
	
	public Course(String courseId, String courseName) {
		this.courseId = courseId;
		this.courseName = courseName;
	}
	
	public Course() {
		
	}
	public String getCourseId() {
		return courseId;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourse(String courseName) {
		this.courseName = courseName;
	}

	public String toString() {
		return courseId + ", " + courseName;
	}
	
}
