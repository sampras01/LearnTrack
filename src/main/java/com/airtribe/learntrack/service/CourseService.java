package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String name, String description, int durationWeeks) {
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, name, description, durationWeeks);
        courses.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> getActiveCourses() {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.isActive().equals(CourseStatus.ACTIVE)) {
                result.add(c);
            }
        }
        return result;
    }

    public Course findById(int id) throws EntityNotFoundException {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course not found with ID " + id);
    }

    public void setCourseActive(int id, CourseStatus active) throws EntityNotFoundException {
        Course c = findById(id);
        c.setActive(active);
    }
}