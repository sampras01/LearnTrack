package com.airtribe.learntrack.util;

public class InputValidator {

	private InputValidator() {
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}