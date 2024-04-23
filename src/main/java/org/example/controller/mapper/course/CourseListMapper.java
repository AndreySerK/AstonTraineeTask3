package org.example.controller.mapper.course;

import org.example.model.Course;
import org.example.controller.dto.course.CourseForUniversityDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = CourseDtoMapper.class)
public interface CourseListMapper {
    List<CourseForUniversityDto> toDtoList(List<Course> courses);
}
