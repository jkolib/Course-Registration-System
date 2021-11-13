# Course-Registration-System

This is a course regristration system that enables a user to login as a student or administrator. Courses are taken from the file MyUniversityCourses.csv on first run of the program and are stored in courses_file after program termination via an object output stream to save any edits. There are no students in the system on first run and any students created during execution are saved in students_file via an object output stream.

Users are required to choose whether they are an admin or student. Admins are required to enter a username and password. The username is "Admin" and the password is "Admin001". Students have the option of registering or logging in using a username and password combo established by the user (or as saved in students_file).

Admins have 2 menus: a courses menu and a reports menu. The courses menu allows an admin to create, delete, and edit a course, display the information of a specific course, and register a student. The reports menu allows an admin to view all courses, view all full courses, create a file that stores all full courses, view all students registered for a given course, view all courses a given student is registered to, and sort courses by the number of students registered.

After registering or logging in, students can view all courses, view all courses that are not full, register or withdraw from a course, and view all courses currently registered to.
