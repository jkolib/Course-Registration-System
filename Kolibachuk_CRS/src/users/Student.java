package users;

import java.io.Serializable;
import java.util.ArrayList;

import courses.Course;
import interfaces.Student_Methods;

public class Student extends User implements Student_Methods, Serializable{
	private static final long serialVersionUID = 1L;
	
	//state
	private ArrayList<Course> enrolled = new ArrayList<Course>();

	//constructor
	public Student(String username, String password, String first_name, String last_name) {
		super(username, password, first_name, last_name);
	}

	//getters and setters
	public ArrayList<Course> getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(ArrayList<Course> enrolled) {
		this.enrolled = enrolled;
	}

	//methods
	@Override
	public void view_current_course() {
		System.out.println("\nViewing all currently enrolled courses:");
		
		//iterate over this student object's enrolled courses arraylist and display info of those courses
		for(Course e:this.enrolled) {
			e.display_info_student();
		}
		
		//if enrolled arraylist is empty, display special message
		if(this.enrolled.isEmpty()) {
			System.out.println("No courses to display");
		}
	}

	@Override
	public void view_not_full(ArrayList<Course> c) {
		System.out.println("\nViewing all courses with available room:");
		
		//iterating over all courses
		for(Course e:c) {
			//checking if course full boolean flag is true or not and printing class name
			if(e.getFull() == false) {
				e.display_info_student();
			}
		}
		
	}

	@Override
	public boolean register(String c_name, int section, ArrayList<Course> courses) {
		//iterating over all courses
		for(Course c:courses) {
			//checking for desired course and also checking to make sure course is not full
			if(c.getC_name().equals(c_name) && c.getSection() == section && !c.getFull()) {
				c.add_student(this); //adding this student to the course
				this.enrolled.add(c); //also adding the desired course to this student's enrolled arraylist
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean withdraw(String c_name, int section, ArrayList<Course> courses) {
		//iterating over all courses
		for(Course c:courses) {
			//checking for desired course
			if(c.getC_name().equals(c_name) && c.getSection() == section) {
				this.enrolled.remove(c); //removing enrolled course from this student's enrolled arraylist
				c.remove_student(this); //removing student from course's student arraylist
				return true;
			}
		}
		return false;
	}
}
