package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static final StudentService studentService = new StudentService();
	private static final CourseService courseService = new CourseService();
	private static final EnrollmentService enrollmentService = new EnrollmentService();

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		runMenuLoop();
		scanner.close();
	}

	private static void runMenuLoop() {
		boolean exit = false;
		while (!exit) {
			printMainMenu();
			try {
				int choice = readInt("Enter choice: ");
				switch (choice) {
				case 1:
					addStudent();
					break;
				case 2:
					viewStudents();
					break;
				case 3:
					searchStudentById();
					break;
				case 4:
					deactivateStudent();
					break;
				case 5:
					addCourse();
					break;
				case 6:
					viewCourses();
					break;
				case 7:
					activateDeactivateCourse();
					break;
				case 8:
					enrollStudentInCourse();
					break;
				case 9:
					viewEnrollmentsForStudent();
					break;
				case 10:
					updateEnrollmentStatus();
					break;
				case 0: {
					System.out.println("Exiting LearnTrack. Goodbye!");
					exit = true;
					break;
				}
				default:
					System.out.println("Invalid option. Please try again.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid number. Please try again.");
			} catch (EntityNotFoundException | InvalidInputException e) {
				System.out.println("Error: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Unexpected error: " + e.getMessage());
			}
		}
	}

	private static void printMainMenu() {
		System.out.println("\n===== LearnTrack - Student & Course Management =====");
		System.out.println("1. Add Student");
		System.out.println("2. View All Students");
		System.out.println("3. Search Student by ID");
		System.out.println("4. Deactivate Student");
		System.out.println("5. Add Course");
		System.out.println("6. View All Courses");
		System.out.println("7. Activate/Deactivate Course");
		System.out.println("8. Enroll Student in Course");
		System.out.println("9. View Enrollments for a Student");
		System.out.println("10. Update Enrollment Status");
		System.out.println("0. Exit");
	}


	private static void addStudent() throws InvalidInputException {
		System.out.println("\n-- Add Student --");
		System.out.print("First name: ");
		String firstName = scanner.nextLine();
		System.out.print("Last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Email (optional, press Enter to skip): ");
		String email = scanner.nextLine();
		System.out.print("Batch: ");
		String batch = scanner.nextLine();

		if (InputValidator.isNullOrEmpty(firstName) || InputValidator.isNullOrEmpty(lastName)
				|| InputValidator.isNullOrEmpty(batch)) {
			throw new InvalidInputException("First name, last name, and batch are required.");
		}

		Student student;
		if (InputValidator.isNullOrEmpty(email)) {
			student = studentService.addStudent(firstName, lastName, batch);
		} else {
			student = studentService.addStudent(firstName, lastName, email, batch);
		}
		System.out.println("Student added: " + student);
	}

	private static void viewStudents() {
		System.out.println("\n-- All Students --");
		List<Student> students = studentService.getAllStudents();
		if (students.isEmpty()) {
			System.out.println("No students found.");
		} else {
			for (Student s : students) {
				System.out.println(s);
			}
		}
	}

	private static void searchStudentById() throws EntityNotFoundException {
		System.out.println("\n-- Search Student by ID --");
		int id = readInt("Enter student ID: ");
		Student student = studentService.findById(id);
		System.out.println("Found: " + student);
	}

	private static void deactivateStudent() throws EntityNotFoundException {
		System.out.println("\n-- Deactivate Student --");
		int id = readInt("Enter student ID: ");
		studentService.deactivateStudent(id);
		System.out.println("Student deactivated.");
	}


	private static void addCourse() throws InvalidInputException {
		System.out.println("\n-- Add Course --");
		System.out.print("Course name: ");
		String name = scanner.nextLine();
		System.out.print("Description: ");
		String description = scanner.nextLine();
		int duration = readInt("Duration (weeks): ");

		if (InputValidator.isNullOrEmpty(name)) {
			throw new InvalidInputException("Course name is required.");
		}

		Course course = courseService.addCourse(name, description, duration);
		System.out.println("Course added: " + course);
	}

	private static void viewCourses() {
		System.out.println("\n-- All Courses --");
		List<Course> courses = courseService.getAllCourses();
		if (courses.isEmpty()) {
			System.out.println("No courses found.");
		} else {
			for (Course c : courses) {
				System.out.println(c);
			}
		}
	}

	private static void activateDeactivateCourse() throws EntityNotFoundException {
		System.out.println("\n-- Activate/Deactivate Course --");
		int id = readInt("Enter course ID: ");
		Course course = courseService.findById(id);
		System.out.println("Current: " + course);
		System.out.print("Set active? (y/n): ");
		String ans = scanner.nextLine().trim().toLowerCase();
		boolean active = ans.startsWith("y");
		if(active)
			courseService.setCourseActive(id, CourseStatus.ACTIVE);
		else
			courseService.setCourseActive(id, CourseStatus.ACTIVE);
		System.out.println("Course updated.");
	}


	private static void enrollStudentInCourse() throws EntityNotFoundException {
		System.out.println("\n-- Enroll Student in Course --");
		int studentId = readInt("Student ID: ");
		int courseId = readInt("Course ID: ");

		studentService.findById(studentId);
		Course course = courseService.findById(courseId);
		if (!course.isActive().equals(CourseStatus.ACTIVE)) {
			System.out.println("Course is not active. Cannot enroll.");
			return;
		}

		Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
		System.out.println("Enrollment created: " + enrollment);
	}

	private static void viewEnrollmentsForStudent() throws EntityNotFoundException {
		System.out.println("\n-- View Enrollments for Student --");
		int studentId = readInt("Student ID: ");
		studentService.findById(studentId);
		List<Enrollment> list = enrollmentService.getEnrollmentsByStudent(studentId);
		if (list.isEmpty()) {
			System.out.println("No enrollments found for this student.");
		} else {
			for (Enrollment e : list) {
				System.out.println(e);
			}
		}
	}

	private static void updateEnrollmentStatus() throws EntityNotFoundException {
		System.out.println("\n-- Update Enrollment Status --");
		int enrollmentId = readInt("Enrollment ID: ");
		System.out.print("New status (ACTIVE/COMPLETED/CANCELLED): ");
		String status = scanner.nextLine().trim().toUpperCase();
		enrollmentService.updateStatus(enrollmentId, EnrollmentStatus.valueOf(status));
		System.out.println("Enrollment status updated.");
	}


	private static int readInt(String prompt) {
		while (true) {
			System.out.print(prompt);
			String line = scanner.nextLine();
			try {
				return Integer.parseInt(line.trim());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer.");
			}
		}
	}


}