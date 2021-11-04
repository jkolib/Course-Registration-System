package interfaces;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;
import users.Student;

public interface Admin_Methods {
	public void create_course(ArrayList<Course> courses, Scanner scan);
	public void delete_course(ArrayList<Course> courses, Scanner scan);
	public void edit_course(ArrayList<Course> courses, Scanner scan);
	public void course_info(String ID, ArrayList<Course> courses);
	public void reg_student(ArrayList<Student> students, Scanner scan);
	public void view_full(ArrayList<Course> courses);
	public void write_full(ArrayList<Course> courses) throws FileNotFoundException;
	public void view_names(String c_name, int section, ArrayList<Course> courses);
	public void view_student_courses(String f_name, String l_name, ArrayList<Student> students);
}
