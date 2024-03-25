package com.r2s.javabackend09.dto.response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.javabackend09.model.Identification;
import com.r2s.javabackend09.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserResponseDTO {
	private Integer id;
	private String name;
	private Long age;
	private Boolean isDeleted;
	private String userName;
	private String identificationName;
	private List<Course> courses;

	@JsonFormat(timezone = "Asia/Ho_Chi_Minh"
//			pattern = "dd-MM-yyyy"
	)
	private Date expiredDate;

	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.age = user.getAge();
		this.isDeleted = user.getIsDeleted();
		this.userName = user.getUserName();
		Identification identification = user.getIdentification();
		if (Objects.nonNull(identification)) {
			this.identificationName = identification.getName();
			this.expiredDate = identification.getExpiredDate();
		}
		this.courses = Course.toCourses(user);
	}

	@Data
	@AllArgsConstructor
	private static class Course {
		private Integer courseId;
		private String courseName;

		public static List<Course> toCourses(User user) {
			return user.getCourses().stream().filter(Objects::nonNull)
					.map(course -> new Course(course.getId(), course.getName())).toList();
		}
	}
}
