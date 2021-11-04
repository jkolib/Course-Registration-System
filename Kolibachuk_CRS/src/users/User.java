package users;

import java.io.Serializable;
import java.util.ArrayList;

import courses.Course;

public abstract class User implements Serializable{
	private static final long serialVersionUID = 1L;
	//state
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	
	//constructor
	public User(String username, String password, String first_name, String last_name) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	//getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	//methods
	public void display_commands() {
		System.out.println("Courses Management");
		System.out.println("1. View all courses");
		System.out.println("2. View all courses that are not full");
		System.out.println("3. Register for a course");
		System.out.println("4. Withdraw from a course");
		System.out.println("5. View all courses currently registered to");
		System.out.println("6. Exit\n");
	}
	
	public void view_all(ArrayList<Course> c) {
		System.out.println("\nViewing all courses:");
		
		//iterating over all courses and displaying info
		for(Course e:c) {
			e.display_info_student();
		}
	}
}
