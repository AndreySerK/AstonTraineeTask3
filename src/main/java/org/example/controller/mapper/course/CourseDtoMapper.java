package org.example.controller.mapper.course;


import org.example.model.Course;
import org.example.controller.dto.course.CourseDto;

import org.example.controller.dto.course.CourseForUniversityDto;
import org.example.controller.dto.course.IncomingCourseDto;
import org.mapstruct.Mapper;

@Mapper
public interface CourseDtoMapper {

    Course toEntity(IncomingCourseDto dto);

    CourseDto toDto(Course course);

    CourseForUniversityDto toUniversityDto(Course course);
}
