package org.example.controller.mapper.student;


import org.example.model.Student;
import org.example.controller.dto.student.StudentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = StudentDtoMapper.class)
public interface StudentListMapper {
    List<StudentDto> toDtoList(List<Student> students);
}
