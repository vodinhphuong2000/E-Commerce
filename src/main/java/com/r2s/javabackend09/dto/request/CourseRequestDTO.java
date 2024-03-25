package com.r2s.javabackend09.dto.request;

import com.r2s.javabackend09.model.Course;

import lombok.Data;

@Data
public class CourseRequestDTO {
	private Integer userId;
	private String name;

	public Course toCourse() {
		Course course = new Course();
		course.setName(name);

		return course;
	}
}
