package com.airtribe.learntrack.entity;

public class Student extends Person {
	private String batch;
	private boolean active = true;


	public Student(int id, String firstName, String lastName, String email, String batch) {
		super(id, firstName, lastName, email);
		this.batch = batch;
	}

	// Overloaded constructor (no email)
	public Student(int id, String firstName, String lastName, String batch) {
		super(id, firstName, lastName, null);
		this.batch = batch;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String getDisplayName() {
		return super.getDisplayName() + " (" + batch + ")";
	}

	@Override
	public String toString() {
		return super.toString() + ", Batch: " + batch + ", Active: " + active;
	}
}