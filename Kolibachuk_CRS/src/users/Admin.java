package users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import courses.Course;
import interfaces.Admin_Methods;

public class Admin extends User implements Admin_Methods, Comparator<Course>{
	private static final long serialVersionUID = 1L;

	//constructor
	public Admin(String username, String password, String first_name, String last_name) {
		super(username, password, first_name, last_name);
	}

	//methods
	@Override
	public void view_all(ArrayList<Course> c) {
		System.out.println("\nViewing all courses:");
		
		//iterating over all courses and displaying info
		for(Course e:c) {
			e.display_info();
		}
	}
	
	@Override
	public void display_commands() {
		//course management menu
		System.out.println("\nCourses Management");
		System.out.println("1. Create a new course");
		System.out.println("2. Delete a course");
		System.out.println("3. Edit a course");
		System.out.println("4. Display information for a given course");
		System.out.println("5. Register a student");
		System.out.println("6. Exit");
	}
	
	public void display_commands_report() {
		//reports menu
		System.out.println("\nReports");
		System.out.println("1. View all courses");
		System.out.println("2. View all full courses");
		System.out.println("3. Write to a file a list of full courses");
		System.out.println("4. View students registered to a specific course");
		System.out.println("5. View all courses a student is registered to");
		System.out.println("6. Sort courses by number of students registered");
		System.out.println("7. Exit");
	}

	@Override
	public void create_course(ArrayList<Course> courses, Scanner scan) {
		System.out.println("\nEnter new course name:"); //getting new course name from user
		String c_name = scan.nextLine();
		
		System.out.println("\nEnter new course ID:"); //getting new course ID from user
		String ID = scan.nextLine();
		
		System.out.println("\nEnter new course maximum number of students:"); //getting new max number of students
		int max = Integer.parseInt(scan.nextLine());
		int current = 0; //setting new course's current student count to 0, course created without students
		
		ArrayList<Student> students = new ArrayList<Student>(); //initializing an arraylist of students
		
		System.out.println("\nEnter new course instructor:"); //getting new course instructor
		String instructor = scan.nextLine();
		
		System.out.println("\nEnter new course section number:"); //getting new course section number
		int section = Integer.parseInt(scan.nextLine());
		
		System.out.println("\nEnter new course location:"); //getting new course location
		String location = scan.nextLine();
		
		//creating new course from inputs and adding to course arraylist
		courses.add(new Course(c_name, ID, max, current, students, instructor, section, location));
		
		System.out.println("\nClass successfully created");
	}

	@Override
	public void delete_course(ArrayList<Course> courses, Scanner scan) {
		//creating helper variables
		int index = 0;
		boolean deleted = false;
		
		//prompting user for name and section number of course to delete
		System.out.println("\nEnter name of course to delete:");
		String c_name = scan.nextLine();
		System.out.println("\nEnter section number of course to delete:");
		int section = Integer.parseInt(scan.nextLine());
		
		//iterating over courses arraylist
		for(Course c:courses) {
			//checking for when a course has same name and section number of desired course
			if(c.getC_name().equals(c_name) && c.getSection() == section) {
				//breaking from loop in order to retain index number and setting deleted to true
				deleted = true;
				break;
			}
			else {
				index++;
			}
		}
		
		//checking if desired course was found
		if(deleted) {
			courses.remove(index); //deleting course based on its index in courses arraylist
			System.out.println("\nClass successfully deleted");
		}
		else {
			System.out.println("Error deleting"); //only prints when course not found
		}
	}

	@Override
	public void edit_course(ArrayList<Course> courses, Scanner scan) {
		boolean exit = false; //helper variable for knowing when to exit while loop
		
		System.out.println("\nEnter course name to edit:"); //prompting for desired course to edit
		String name = scan.nextLine();
		System.out.println("\nEnter course section to edit:");
		int section = Integer.parseInt(scan.nextLine());
		
		Course course = null;
		
		//iterating over all courses
		for(Course c:courses) {
			//checking for when desired course and section found
			if(c.getC_name().equals(name) && c.getSection() == section) {
				course = c;
			}
		}
		
		//sentinel controlled loop
		while(!exit) {
			System.out.println("\nCourse Editing Menu");
			System.out.println("1. Edit max number of students");
			System.out.println("2. Edit instructor");
			System.out.println("3. Edit section number");
			System.out.println("4. Edit location");
			System.out.println("5. Exit");
			
			//getting user's input based on menu
			String inp = scan.nextLine();
			
			//selecting case based on user input
			switch(inp) {
				case "1":
					System.out.println("\nEnter new max number of students:"); //getting new max from user
					int max = Integer.parseInt(scan.nextLine());
					
					course.setMax(max); //setting new max for course
					//checking if new max results in a class becoming full
					if(course.getMax() <= course.getCurrent()) {
						course.setFull(true); //changing course to be full as a result
					}
					
					System.out.println("\nNew max set");
					break;
					
				case "2":
					System.out.println("\nEnter a new instructor:"); //getting new instructor from user
					String instructor = scan.nextLine();
					
					course.setInstructor(instructor); //setting new instructor for course
					System.out.println("\nNew instructor set");
					break;
					
				case "3":
					System.out.println("\nEnter a new section number:"); //getting new section number from user
					section = Integer.parseInt(scan.nextLine());
					
					course.setSection(section); //setting new section number
					System.out.println("\nNew section number set");
					break;
					
				case "4":
					System.out.println("\nEnter a new location:"); //getting new location from user
					String location = scan.nextLine();
					
					course.setLocation(location); //setting new location
					System.out.println("\nNew location set");
					break;
				
				case "5":
					System.out.println("\nExiting menu...");
					exit = true; //setting sentinel true to exit while loop
			}
		}
	}

	@Override
	public void course_info(String ID, ArrayList<Course> courses) {
		System.out.println("\nDisplaying all courses with ID: "+ID);
		
		//iterating over every course
		for(Course c:courses) {
			//checking if a course has desired ID and printing out info
			if(c.getID().equals(ID)) {
				c.display_info();
			}
		}
	}

	@Override
	public void reg_student(ArrayList<Student> students, Scanner scan) {
		System.out.println("\nEnter the fist name of the new student:"); //getting new student's first name
		String f_name = scan.nextLine();
		
		System.out.println("\nEnter the last name of the new student:"); //getting new student's last name
		String l_name = scan.nextLine();
		
		System.out.println("\nEnter the username of the new student:"); //getting new student's username
		String user = scan.nextLine();
		
		System.out.println("\nEnter the password of the new student:"); //getting new student's password
		String pass = scan.nextLine();
		
		//creating new student object based on inputs and adding to students arraylist
		students.add(new Student(user, pass,f_name, l_name));
		
		System.out.println("\nStudent successfully registered");
	}

	@Override
	public void view_full(ArrayList<Course> courses) {
		boolean none_full = true; //helper variable
		System.out.println("\nViewing all full courses:");
		
		//iterating over every course
		for(Course c:courses) {
			//checking if a course is full based on the value of full
			if(c.getFull()) {
				//displaying course info and setting boolean flag false
				none_full = false;
				c.display_info();
			}
		}
		
		//checks if any courses were full
		if(none_full) {
			System.out.println("No courses to display");
		}
	}

	@Override
	public void write_full(ArrayList<Course> courses) throws FileNotFoundException {
		File f = new File("list_of_full_courses"); //initializing new file
		boolean created = true;
		
		//trying to create file
		try {
			f.createNewFile();
		} catch (Exception e) {
			System.out.println("\nError creating file");
			created = false;
		}
		
		PrintWriter pw = new PrintWriter(f); //creating printwriter
		
		//trying to write to file
		try {
			//iterating over each course
			for(Course c:courses) {
				//checking if course is full
				if(c.getFull()) {
					//writing to file
					pw.println(c.getC_name()+", section "+c.getSection());
					pw.flush();
				}
			}
		} catch (Exception e) {
			System.out.println("\nError writing to file");
			created = false; //setting flag to false if error writing
			pw.close();
		}
		
		//checking if file created
		if(created) {
			System.out.println("\nFile created successfully");
		}
	}

	@Override
	public void view_names(String c_name, int section, ArrayList<Course> courses) {
		System.out.println("\nViewing all students registered for "+c_name+", section "+section);
		boolean no_students = false; //boolean flag
		
		//iterating over each course
		for(Course c:courses) {
			//check if course has desired name and section number
			if(c.getC_name().equals(c_name) && c.getSection() == section) {
				//check if course's students arraylist is empty and exiting loop/changing flag
				if(c.getStudents().isEmpty()) {
					no_students = true;
					break;
				}
				else {
					//iterating over every student in course's arraylist and displaying first and last names
					for(Student s:c.getStudents()) {
						System.out.println(s.getFirst_name()+" "+s.getLast_name());
					}
				}
			}
		}
		
		//checks if no students
		if(no_students) {
			System.out.println("No students registered");
		}
	}

	@Override
	public void view_student_courses(String f_name, String l_name, ArrayList<Student> students) {
		System.out.println("\nViewing all of "+f_name+" "+l_name+"'s courses:");
		
		//iterating over every student
		for(Student s:students) {
			//checking if a student has desired first and last name
			if(s.getFirst_name().equals(f_name) && s.getLast_name().equals(l_name)) {
				ArrayList<Course> courses = s.getEnrolled(); //getting student's enrolled courses
				
				//iterating over each course and displaying info
				for(Course c:courses) {
					c.display_info();
				}
			}
		}
	}

	@Override
	public int compare(Course c1, Course c2) {
		//compare method for comparator, comparing current numbers of each course's students
		return c2.getCurrent() - c1.getCurrent();
	}
}
