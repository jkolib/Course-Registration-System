package courses;

import java.io.Serializable;
import java.util.ArrayList;

import users.Student;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//state
	private String c_name;
	private String ID;
	private int max;
	private int current;
	private ArrayList<Student> students;
	private String instructor;
	private int section;
	private String location;
	private boolean full;
	
	//constructors
	public Course() {
		
	}
	
	public Course(String c_name, String iD, int max, int current, ArrayList<Student> students, 
			String instructor, int section, String location) {
		this.c_name = c_name;
		this.ID = iD;
		this.max = max;
		this.current = current;
		this.students = students;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
		this.full = false;
	}

	//getters and setters
	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public boolean getFull() {
		return this.full;
	}
	
	public void setFull(boolean full) {
		this.full = full;
	}

	//methods
	public void display_info_student() {
		System.out.print("Course Name: "+this.c_name);
		System.out.print(", Course ID: "+this.ID);
		System.out.print(", Instructor: "+this.instructor);
		System.out.print(", Section: "+this.section);
		System.out.println(", Location: "+this.location);
	}
	
	public void display_info() {
		System.out.print("Course Name: "+this.c_name);
		System.out.print(", Course ID: "+this.ID);
		System.out.print(", Max Number of Students: "+this.max);
		System.out.println(", Current Number of Students: "+this.current);
	}
	
	public void display_students() {
		//iterating over this course object's arraylist of students
		for(Student s : this.students) {
			System.out.println(s.getFirst_name()+" "+s.getLast_name());
		}
	}
	
	public void add_student(Student s) {
		//adding student object to this course object's students arraylist
		this.students.add(s);
		this.current++; //also incrementing current count of students
		
		//checking if adding a student results in class being full/overfull and setting full boolean accordingly
		if(this.current >= this.max) {
			this.full = true;
		}
	}
	
	public void remove_student(Student s) {
		//removing student object from this course object's students arraylist
		this.students.remove(s);
		this.current--; //also decrementing current count of students
		
		//checking if removing a student results in class no longer being full and changing full value accordingly
		if(this.current < this.max) {
			this.full = false;
		}
	}
}
