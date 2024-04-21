package org.example.servlet.mapper.course;


import org.example.model.Course;
import org.example.servlet.dto.course.CourseDto;

import org.example.servlet.dto.course.CourseForUniversityDto;
import org.example.servlet.dto.course.IncomingCourseDto;
import org.mapstruct.Mapper;

@Mapper
public interface CourseDtoMapper {

    Course toEntity(IncomingCourseDto dto);

    CourseDto toDto(Course course);

    CourseForUniversityDto toUniversityDto(Course course);
}
