package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import courses.Course;
import users.Admin;
import users.Student;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//setting up arraylists for students and courses
		ArrayList<Course> courses = null;
		ArrayList<Student> students = null;
		boolean no_data = false; //boolean flag
		
		//trying to read in arraylists for courses and students
		try {
			ObjectInputStream c_input = new ObjectInputStream(new FileInputStream("courses_file"));
			ObjectInputStream s_input = new ObjectInputStream(new FileInputStream("students_file"));
			courses = (ArrayList<Course>) c_input.readObject(); //setting arraylist for courses
			students = (ArrayList<Student>) s_input.readObject(); //setting arraylist for students
			
			c_input.close();
			s_input.close();
		} catch (Exception e) {
			no_data = true; //changing flag if no course or student files found
		}
			
		//checking if no data and creating new course and student data accordingly
		if(no_data) {
			courses = new ArrayList<Course>();
			students = new ArrayList<Student>();
			
			//helper variables for reading in from csv file
			String line = "";
			int line_num = 0;
			String split_on = ",";
			
			//creating bufferedreader
			BufferedReader br = new BufferedReader(new FileReader("MyUniversityCourses.csv"));
			
			//reading from csv
			while((line = br.readLine()) != null) {
				String[] info = line.split(split_on);
				line_num++;
				
				//checking for special case and skipping that iteration
				if(line_num == 1) {
					continue;
				}
				else {
					//creating and adding courses to arraylist from csv data
					courses.add(new Course(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), 
							new ArrayList<Student>(), info[5], Integer.parseInt(info[6]), info[7]));
				}
			}
			br.close();
		}
		
		//creating scanner
		final Scanner scan = new Scanner(System.in);
		boolean incorrect_input = true; //boolean flag
		
		//sentinel controlled loop
		while(incorrect_input) {
			System.out.println("Welcome, are you an admin or student:"); //prompting user for admin or student
			String input = scan.nextLine().toLowerCase();
			
			switch(input) {
				case "admin":
					Admin_Environment(courses, students, scan); //running admin method
					incorrect_input = false; //exiting loop
					break;
					
				case "student":
					Student_Environment(courses, students, scan); //running student method
					incorrect_input = false; //exiting loop
					break;
					
				default:
					System.out.println("Input not recognized");
			}
		}
		
		//creating output streams
		ObjectOutputStream c_output = new ObjectOutputStream(new FileOutputStream("courses_file"));
		ObjectOutputStream s_output = new ObjectOutputStream(new FileOutputStream("students_file"));
		
		//writing courses and students arraylists to files
		c_output.writeObject(courses);
		s_output.writeObject(students);
		
		//closing scanner and output streams
		c_output.close();
		s_output.close();
		scan.close();
	}
	
	public static void Student_Environment(ArrayList<Course> courses, ArrayList<Student> students, Scanner scan) {
		boolean exit = false; //boolean flag
		Student student = null; //initializing student
		
		System.out.println("\n1. Login\n2. New User"); //asking whether user is new or not
		int choice = Integer.parseInt(scan.nextLine());
		
		//switching for new user or not
		switch(choice) {
			case 1: {
				boolean not_exists = true; //boolean flag
				
				//loop will run until user enters correct username and pass
				while(not_exists) {
					System.out.println("\nPlease enter your username:"); //getting username
					String user = scan.nextLine();
					
					System.out.println("\nPlease enter your password:"); //getting password
					String pass = scan.nextLine();
					
					//iterating over all students
					for(Student s:students) {
						//checking if username and password align with any student
						if(s.getUsername().equals(user) && s.getPassword().equals(pass)) {
							student = s; //setting student
							not_exists = false;
						}
					}
					
					if(not_exists) {
						System.out.println("\nIncorrect username or password");
					}
				}
				
				break;
			}
			
			case 2: {
				System.out.println("\nPlease enter your first name:"); //prompting new student's first name
				String f_name = scan.nextLine();
				
				System.out.println("\nPlease enter your last name:"); //prompting new student's last name
				String l_name = scan.nextLine();
				
				System.out.println("\nPlease enter your username:"); //prompting new student's username
				String user = scan.nextLine();
				
				System.out.println("\nPlease enter your password:"); //prompting new student's password
				String pass = scan.nextLine();

				//creating new student from inputs and adding to students arraylist
				student = new Student(user, pass, f_name, l_name);
				students.add(student);
				break;
			}
		}
		
		//sentinel controlled loop
		while(!exit) {
			//greeting message and displaying commands
			System.out.println("\nWelcome, "+student.getFirst_name()+".");
			student.display_commands();
			choice = Integer.parseInt(scan.nextLine()); //getting user response
			
			//switching on user response
			switch(choice) {
				case 1:
					student.view_all(courses); //running student method
					break;
					
				case 2:
					student.view_not_full(courses); //running student method
					break;
					
				case 3:
					System.out.println("\nPlease enter desired course name:"); //getting desired course name
					String c_name_reg = scan.nextLine();
					
					System.out.println("\nPlease enter desired section number:"); //getting desired course section
					int section_reg = Integer.parseInt(scan.nextLine());
					
					//registering student
					boolean registered = student.register(c_name_reg, section_reg, courses);
					
					//checking if registration was successful
					if(registered) {
						System.out.println("\nYou have been registered for "+c_name_reg+", section "+
								section_reg);
					}
					else {
						System.out.println("\nThere was an error registering for "+c_name_reg+", section "+
								section_reg);
					}
					
					break;
					
				case 4:
					System.out.println("\nPlease enter course name to drop:"); //getting desired course name
					String c_name_withdraw = scan.nextLine();
					
					System.out.println("\nPlease enter section number to drop:"); //getting desired section number
					int section_withdraw = Integer.parseInt(scan.nextLine());
					
					//withdrawing student from course
					boolean withdrew = student.withdraw(c_name_withdraw, section_withdraw, courses);
					
					//checking if operation was successful
					if(withdrew) {
						System.out.println("\nYou have been withdrawn for "+c_name_withdraw
								+", section "+section_withdraw);
					}
					else {
						System.out.println("\nThere was an error withdrawing from "+c_name_withdraw
								+", section "+section_withdraw);
					}
					
					break;
					
				case 5:
					student.view_current_course(); //running student method
					break;
					
				case 6:
					System.out.println("\nExiting...");
					exit = true; //changing boolean flag to exit loop
					break;
					
				default:
					System.out.println("\nInput not recognized");
			}
		}
	}
	
	public static void Admin_Environment(ArrayList<Course> courses, ArrayList<Student> students, Scanner scan) {
		//boolean flags
		boolean exit = false;
		boolean is_admin = false;
		
		Admin admin = null; //initializing admin
		
		//sentinel controlled loop, will continue until correct username and password provided
		while(!is_admin) {
			System.out.println("\nPlease enter admin username:"); //getting username
			String user = scan.nextLine();
			
			System.out.println("\nPlease enter admin password:"); //getting password
			String pass = scan.nextLine();
			
			//checking if username and password were entered correctly
			if(user.equals("Admin") && pass.equals("Admin001")) {
				admin = new Admin("Admin", "Admin001", null, null); //creating admin
				is_admin = true; //changing flag
			}
			else {
				System.out.println("\nIncorrect username or password");
			}
		}
		
		System.out.println("\n1. Courses Menu\n2. Reports Menu"); //prompting user for menu choice
		int choice = Integer.parseInt(scan.nextLine());
		
		//switching on menu choice
		switch(choice) {
			case 1:
				//loop runs until user exits
				while(!exit) {
					admin.display_commands();
					choice = Integer.parseInt(scan.nextLine()); //displaying commands and prompting response
					
					//switching on course menu choice
					switch(choice) {
						case 1:
							admin.create_course(courses, scan); //creating course
							break;
							
						case 2:
							admin.delete_course(courses, scan); //deleting course
							break;
							
						case 3:
							admin.edit_course(courses, scan); //editing course
							break;
							
						case 4:
							System.out.println("\nEnter ID of desired course:"); //prompting for course ID
							String ID = scan.nextLine();
							
							admin.course_info(ID, courses); //displaying by course ID
							break;
							
						case 5:
							admin.reg_student(students, scan); //creating student
							break;
							
						case 6:
							System.out.println("\nExiting...");
							exit = true; //changing flag to exit loop
							break;
							
						default:
							System.out.println("\nInput not recognized");
					}
				}
				break;
			
			case 2:
				//loops until user enters exit case
				while(!exit) {
					admin.display_commands_report();
					choice = Integer.parseInt(scan.nextLine()); //displaying commands and prompting response
					
					//switching on reports menu response
					switch(choice) {
						case 1:
							admin.view_all(courses); //viewing all courses
							break;
							
						case 2:
							admin.view_full(courses); //viewing all full courses
							break;
							
						case 3:
							try {
								admin.write_full(courses); //trying to write to file full courses
							} catch (FileNotFoundException e) {
								System.out.println("\nError writing to file");
							}
							break;
							
						case 4:
							System.out.println("\nPlease enter the course name:"); //prompting for desired name
							String c_name = scan.nextLine();
							
							System.out.println("\nPlease enter the desired section number:"); //prompting for desired section number
							int section = Integer.parseInt(scan.nextLine());
							
							admin.view_names(c_name, section, courses); //viewing all names in course
							break;
							
						case 5:
							System.out.println("\nEnter student's first name:"); //prompting for first name
							String f_name = scan.nextLine();
							
							System.out.println("\nEnter student's last name:"); //prompting for last name
							String l_name = scan.nextLine();
							
							admin.view_student_courses(f_name, l_name, students); //viewing all courses a student is registered to
							break;
							
						case 6:
							System.out.println("\nCourses sorted by number of currently registered students");
							Collections.sort(courses, admin); //sorting courses so courses with more students come first
							break;
							
						case 7:
							System.out.println("\nExiting...");
							exit = true; //changing flag to exit loop
							break;
							
						default:
							System.out.println("\nInput not recognized");
					}
				}
		}
	}
}
