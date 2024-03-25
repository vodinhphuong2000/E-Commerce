package com.r2s.javabackend09.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.dto.request.CourseRequestDTO;
import com.r2s.javabackend09.exception.UserNotFoundException;
import com.r2s.javabackend09.model.Course;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.repository.CourseRepository;
import com.r2s.javabackend09.repository.UserRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	public Course addCourse(CourseRequestDTO courseDTO) throws UserNotFoundException {
		// validate course dto
		// ...

		// 1. luu thong qua user
//		Optional<User> foundUserOptional = this.userRepository.findById(courseDTO.getUserId());
//		if (foundUserOptional.isEmpty()) {
//			throw new UserNotFoundException();
//		}
//		User foundUser = foundUserOptional.get();
//		Course course = courseDTO.toCourse();
//		course.setUser(foundUser);
//		foundUser.getCourses().add(course);
//		this.userRepository.save(foundUser);
//		return course;

		// 2. luu thong qua course
		Optional<User> foundUserOptional = this.userRepository.findById(courseDTO.getUserId());
		if (foundUserOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		User foundUser = foundUserOptional.get();
		Course course = courseDTO.toCourse();
		course.setUser(foundUser);
		return this.courseRepository.save(course);
	}
}
