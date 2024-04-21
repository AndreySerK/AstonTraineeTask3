package org.example.servlet.mapper.course;

import org.example.model.Course;
import org.example.servlet.dto.course.CourseForUniversityDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = CourseDtoMapper.class)
public interface CourseListMapper {
    List<CourseForUniversityDto> toDtoList(List<Course> courses);
}
