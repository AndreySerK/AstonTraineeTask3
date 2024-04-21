package org.example.servlet.mapper.student;


import org.example.model.Student;
import org.example.servlet.dto.student.StudentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = StudentDtoMapper.class)
public interface StudentListMapper {
    List<StudentDto> toDtoList(List<Student> students);
}
