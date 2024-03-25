package com.r2s.javabackend09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.dto.request.CourseRequestDTO;
import com.r2s.javabackend09.dto.response.CourseResponseDTO;
import com.r2s.javabackend09.exception.UserNotFoundException;
import com.r2s.javabackend09.service.CourseService;
import com.r2s.javabackend09.utils.ResponseCode;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@PostMapping("")
	public ResponseEntity<?> addCourse(@RequestBody CourseRequestDTO course) {
		try {
			return BaseResponseController.success(new CourseResponseDTO(this.courseService.addCourse(course)));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		}
	}

}
