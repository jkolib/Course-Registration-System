package interfaces;

import java.util.ArrayList;

import courses.Course;

public interface Student_Methods {
	public void view_not_full(ArrayList<Course> c);
	public boolean register(String c_name, int section, ArrayList<Course> courses);
	public boolean withdraw(String c_name, int section, ArrayList<Course> courses);
	public void view_current_course();
}
