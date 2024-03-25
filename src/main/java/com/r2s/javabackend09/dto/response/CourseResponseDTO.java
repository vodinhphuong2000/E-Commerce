package com.r2s.javabackend09.dto.response;

import java.util.Objects;

import com.r2s.javabackend09.model.Course;
import com.r2s.javabackend09.model.User;

import lombok.Data;

@Data
public class CourseResponseDTO {
	private Integer id;
	private String name;
	private Integer userId;

	public CourseResponseDTO(Course course) {
		this.id = course.getId();
		this.name = course.getName();
		User user = course.getUser();
		if (Objects.nonNull(user)) {
			this.userId = user.getId();
		}
	}
}
