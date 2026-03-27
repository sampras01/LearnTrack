package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

	private final List<Student> students = new ArrayList<>();

	public Student addStudent(String firstName, String lastName, String email, String batch) {
		int id = IdGenerator.getNextStudentId();
		Student student = new Student(id, firstName, lastName, email, batch);
		students.add(student);
		return student;
	}

	public Student addStudent(String firstName, String lastName, String batch) {
		int id = IdGenerator.getNextStudentId();
		Student student = new Student(id, firstName, lastName, batch);
		students.add(student);
		return student;
	}

	public List<Student> getAllStudents() {
		return new ArrayList<>(students);
	}

	public List<Student> getActiveStudents() {
		List<Student> result = new ArrayList<>();
		for (Student s : students) {
			if (s.isActive()) {
				result.add(s);
			}
		}
		return result;
	}

	public Student findById(int id) throws EntityNotFoundException {
		for (Student s : students) {
			if (s.getId() == id) {
				return s;
			}
		}
		throw new EntityNotFoundException("Student not found with ID " + id);
	}

	public void deactivateStudent(int id) throws EntityNotFoundException {
		Student s = findById(id);
		s.setActive(false);
	}
}