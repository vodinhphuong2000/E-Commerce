package com.r2s.javabackend09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.javabackend09.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
