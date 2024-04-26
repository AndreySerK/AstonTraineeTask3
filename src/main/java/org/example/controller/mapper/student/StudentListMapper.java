package org.example.controller.mapper.student;


import org.example.model.Student;
import org.example.controller.dto.student.StudentDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = StudentDtoMapper.class)
public interface StudentListMapper {
    List<StudentDto> toDtoList(List<Student> students);
}
