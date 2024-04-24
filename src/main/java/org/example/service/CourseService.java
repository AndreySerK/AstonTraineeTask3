package org.example.service;

import org.example.controller.dto.course.CourseDto;
import org.example.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService extends SimpleService<Course,Integer> {
}
