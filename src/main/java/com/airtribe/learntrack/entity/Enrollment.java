package com.airtribe.learntrack.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Enrollment {
	private int id;
	private int studentId;
	private int courseId;
	private LocalDate enrollmentDate;
	private EnrollmentStatus status; 

	public Enrollment() {
	}

	public Enrollment(int id, int studentId, int courseId, EnrollmentStatus status) {
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.enrollmentDate = LocalDate.now();
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public EnrollmentStatus getStatus() {
		return status;
	}

	public void setStatus(EnrollmentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return "ID: " + id + ", StudentID: " + studentId + ", CourseID: " + courseId + ", Date: "
				+ enrollmentDate.format(fmt) + ", Status: " + status.name();
	}
}