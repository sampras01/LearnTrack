package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

	private final List<Enrollment> enrollments = new ArrayList<>();

	public Enrollment enrollStudent(int studentId, int courseId) {
		int id = IdGenerator.getNextEnrollmentId();
		Enrollment enrollment = new Enrollment(id, studentId, courseId,EnrollmentStatus.ACTIVE);
		enrollments.add(enrollment);
		return enrollment;
	}

	public List<Enrollment> getAllEnrollments() {
		return new ArrayList<>(enrollments);
	}

	public List<Enrollment> getEnrollmentsByStudent(int studentId) {
		List<Enrollment> result = new ArrayList<>();
		for (Enrollment e : enrollments) {
			if (e.getStudentId() == studentId) {
				result.add(e);
			}
		}
		return result;
	}

	public Enrollment findById(int id) throws EntityNotFoundException {
		for (Enrollment e : enrollments) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new EntityNotFoundException("Enrollment not found with ID " + id);
	}

	public void updateStatus(int enrollmentId, EnrollmentStatus newStatus) throws EntityNotFoundException {
		Enrollment e = findById(enrollmentId);
		e.setStatus(newStatus);
	}
}