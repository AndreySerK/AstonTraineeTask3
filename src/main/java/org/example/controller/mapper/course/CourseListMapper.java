package org.example.controller.mapper.course;

import org.example.model.Course;
import org.example.controller.dto.course.CourseForUniversityDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = CourseDtoMapper.class)
public interface CourseListMapper {
    List<CourseForUniversityDto> toDtoList(List<Course> courses);
}
