package lab7;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Driver {
	private List<Student>students = new ArrayList<Student>();
	private List<Course>courses = new ArrayList<Course>();
	private List<FacultyMember>facultyMembers = new ArrayList<FacultyMember>();

	public void menu() {
		String[] choices = {"Quit", "Add a Student", "Add a faculty member", "Add a Course", "delete a Student",
				"Delete a faculty member", "List courses", "List Students", "List faculty Members", "Students Profile", "Faculty member Profile"}; 
		int choice;
		do {
			choice = JOptionPane.showOptionDialog(null, "Main menu", "Main Menu",
					0, JOptionPane.QUESTION_MESSAGE, null, choices, null);

			switch(choice) {
			case 1:
				addStudent();
				break;
			case 2:
				addFacultyMember();
				break;
			case 3: 
				addCourses();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				deleteFacultyMember();
				break;
			case 6:
				listCourses();
				break;
			case 7:
				listStudents();
				break;
			case 8:
				listFacultyMembers();
				break;
			case 9:
				studentDriver();
				break;
			case 10:
				facultyDriver();
				break;
			}
		}while (choice!=0);
	}

	public void hardCode() {
		students.add(new Student("Adekola", new Address(17050, "Passage", "Bell", "CA", "USA"), 305));
		students.add(new Student("Kenny", new Address(50, "Havec", "Garden", "PA", "USA"), 309));
		students.add(new Student("Alaba", new Address(17, "Park", "Mills", "GA", "USA"), 307));

		courses.add(new Course("ET180", "Intro to engineering"));
		courses.add(new Course("PH180", "Intro to Physics"));
		courses.add(new Course("CS180", "Intro to Computer"));

		facultyMembers.add(new FacultyMember("Tayewo", new Address(17050, "Passage", "Flower", "CA", "USA"), 101));
		facultyMembers.add(new FacultyMember("Kehinde", new Address(1535, "Monte Carlo", "Las Vegas", "NV", "USA"), 102));
		facultyMembers.add(new FacultyMember("Idowu", new Address(5050, "BroadWay", "New York", "NY", "USA"), 103));

	}

	private void addStudent() {
		String name = JOptionPane.showInputDialog("Enter Student name");
		int streetNum = Integer.parseInt(JOptionPane.showInputDialog("Enter Student Address: Street number"));
		String streetName = JOptionPane.showInputDialog("Enter Student Address: Street Name");
		String city = JOptionPane.showInputDialog("Enter Student Address: City");
		String state = JOptionPane.showInputDialog("Enter Student Address: State");
		String country = JOptionPane.showInputDialog("Enter Student Address: country");
		Address address = new Address(streetNum, streetName, city, state, country);
		int cin = Integer.parseInt(JOptionPane.showInputDialog("Enter Student cin"));
		students.add(new Student(name, address, cin));
	}
	private void addFacultyMember() {
		String name = JOptionPane.showInputDialog("Enter Faculty member name");
		int streetNum = Integer.parseInt(JOptionPane.showInputDialog("Enter Faculty member Address: Street number"));
		String streetName = JOptionPane.showInputDialog("Enter Faculty member Address: Street Name");
		String city = JOptionPane.showInputDialog("Enter Faculty member Address: City");
		String state = JOptionPane.showInputDialog("Enter Faculty member Address: State");
		String country = JOptionPane.showInputDialog("Enter Faculty member Address: country");
		Address address = new Address(streetNum, streetName, city, state, country);
		int empId = Integer.parseInt(JOptionPane.showInputDialog("Enter Faculty member employee Id"));
		facultyMembers.add(new FacultyMember(name, address, empId));
	}

	private void addCourses() {
		String courseId = JOptionPane.showInputDialog("Enter the course Id");
		String courseName = JOptionPane.showInputDialog("Enter the course Name");
		courses.add(new Course(courseId, courseName));
	}

	private void deleteStudent() {
		if (students.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are no students in the system");
		} else {
			listStudents();
			Student holdStud = new Student();
			boolean studentExists = false;
			int cin = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the student cin"));
			for(Student s: students) {
				if (cin == s.getCin()) {
					holdStud = s;
					studentExists = true;
				}
			}
			if (studentExists == true) {
				students.remove(holdStud);
			} else {
				JOptionPane.showMessageDialog(null, "That Student does not exist");
			}
		}	
	}

	private void deleteFacultyMember() {
		if (facultyMembers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are no faculty members in the system");
		} else {
			listFacultyMembers();
			FacultyMember holdFalc = new FacultyMember();
			boolean falcExists = false;
			int empId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the employee ID"));
			for(FacultyMember f: facultyMembers) {
				if (empId == f.getEmpId()) {
					holdFalc = f;
					falcExists = true;
				}
			}
			if (falcExists == true) {
				facultyMembers.remove(holdFalc);
			} else {
				JOptionPane.showMessageDialog(null, "That Faculty Member does not exist");
			}
		}	
	}


	private void listCourses() {
		StringBuilder sB = new StringBuilder("These are the current list of Courses Available:\n");
		if (courses.isEmpty()) {
			sB.append("There are no courses");
		} else {
			for (Course c: courses)
				sB.append(c + "\n");
		}
		JOptionPane.showMessageDialog(null, sB);

	}

	private void listStudents() {
		StringBuilder sB = new StringBuilder("These are the current list of Students:\n");
		if (students.isEmpty()) {
			sB.append("There are no students");
		} else {
			for (Student s: students)
				sB.append(s + "\n");
		}
		JOptionPane.showMessageDialog(null, sB);
	}

	private void listStudentSchedule(Student hold) {
		StringBuilder sB = new StringBuilder("These are the current list of Courses:\n");
		if (hold.getCourse().isEmpty()) {
			sB.append("This student doesn't offer any course");
		} else {
			for (Course c : hold.getCourse()) 
				sB.append(c + "\n");
		}
		JOptionPane.showMessageDialog(null, sB);
	}


	private void studentAddCourse(Student s) {
		listCourses();
		Course holdCourse = new Course();
		boolean courseExists = false;
		String courseId = JOptionPane.showInputDialog(null, "Enter the course  Id");
		for(Course c : courses) {
			if (courseId.equals(c.getCourseId())) {
				holdCourse = c;
				courseExists = true;
			}
		}
		if (courseExists == true) {
			s.setCourse(holdCourse);
		}  else {
			JOptionPane.showMessageDialog(null, "That Course does not exist");
		}
	}

	private void studentDeleteCourse(Student s) {
		if (s.getCourse().isEmpty()) {
			JOptionPane.showMessageDialog(null, "This Student doesnt offer any course");
		} else {
			listStudentSchedule(s);
			Course holdCourse = new Course();
			boolean courseExists = false;
			String courseId = JOptionPane.showInputDialog(null, "Enter the course  Id");
			for(Course c : s.getCourse()) {
				if (courseId.equals(c.getCourseId())) {
					holdCourse = c;
					courseExists = true;
				}
			}
			if (courseExists == true) {
				s.getCourse().remove(holdCourse);
			} else {
				JOptionPane.showMessageDialog(null, "That Course does not exist");
			}
		}
	}

	private void studentDriver() {
		Student hold = new Student();
		boolean idExists = false;
		int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your student Id"));
		for (Student s : students) {
			if(id == s.getCin()) {
				hold = s;
				idExists = true;
			}
		}
		if(idExists == true) {
			String[] choices = {"Go to main menu", "List courses", "Add a course", "delete a course"};
			int choice;
			do {
				choice = JOptionPane.showOptionDialog(null, "Welcome " + hold.getName(), "Choose a task",
						0, JOptionPane.QUESTION_MESSAGE, null, choices, null);
				switch(choice) {
				case 0:
					break;
				case 1:
					listStudentSchedule(hold);
					break;
				case 2:
					studentAddCourse(hold);
					break;
				case 3:
					studentDeleteCourse(hold);
					break;
				}
			}while(choice !=0);

		} else {
			JOptionPane.showMessageDialog(null, "That CIN does not exist");
		} 
	}

	private void facultyDriver() {
		FacultyMember hold = new FacultyMember();
		boolean idExists = false;
		int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your employee Id"));
		for (FacultyMember f : facultyMembers) {
			if(id == f.getEmpId()) {
				hold = f;
				idExists = true;
			}
		}
		if(idExists == true) {
			String[] choices = {"Go to main menu", "List courses", "Add a course", "delete a course"};
			int choice;
			do {
				choice = JOptionPane.showOptionDialog(null, "Welcome " + hold.getName(), "Choose a task",
						0, JOptionPane.QUESTION_MESSAGE, null, choices, null);
				switch(choice) {
				case 0:
					break;
				case 1:
					listSchedule(hold);
					break;
				case 2:
					facultyAddCourse(hold);
					break;
				case 3:
					facultyDeleteCourse(hold);
					break;
				}
			}while(choice !=0);

		} else {
			JOptionPane.showMessageDialog(null, "That CIN does not exist");
		} 

	}

	private void listSchedule(FacultyMember hold) {
		StringBuilder sB = new StringBuilder("These are the current list of Courses taught:\n");
		if (hold.getCourse().isEmpty()) {
			sB.append("This FacultyMember doesn't teach any course");
		} else {
			for (Course c : hold.getCourse()) 
				sB.append(c + "\n");
		}
		JOptionPane.showMessageDialog(null, sB);

	}
	private void facultyAddCourse(FacultyMember f) {
		listCourses();
		Course holdCourse = new Course();
		boolean courseExists = false;
		String courseId = JOptionPane.showInputDialog(null, "Enter the course  Id");
		for(Course c : courses) {
			if (courseId.equals(c.getCourseId())) {
				holdCourse = c;
				courseExists = true;
			}
		}
		if (courseExists == true) {
			f.setCourse(holdCourse);
		}  else {
			JOptionPane.showMessageDialog(null, "That Course does not exist");
		}

	}
	private void facultyDeleteCourse(FacultyMember f) {
		if (f.getCourse().isEmpty()) {
			JOptionPane.showMessageDialog(null, "This Faculty Member doesnt teach any course");
		} else {
			listSchedule(f);
			Course holdCourse = new Course();
			boolean courseExists = false;
			String courseId = JOptionPane.showInputDialog(null, "Enter the course  Id");
			for(Course c : f.getCourse()) {
				if (courseId.equals(c.getCourseId())) {
					holdCourse = c;
					courseExists = true;
				}
			}
			if (courseExists == true) {
				f.getCourse().remove(holdCourse);
			} else {
				JOptionPane.showMessageDialog(null, "That Course does not exist");
			}
		}
	}

	private void listFacultyMembers() {
		StringBuilder sB = new StringBuilder("These are the current list of Faculty Members\n");
		if (facultyMembers.isEmpty()) {
			sB.append("There are no faculty members");
		} else {
			for (FacultyMember f: facultyMembers)
				sB.append(f + "\n");
		}
		JOptionPane.showMessageDialog(null, sB);
	}

}

