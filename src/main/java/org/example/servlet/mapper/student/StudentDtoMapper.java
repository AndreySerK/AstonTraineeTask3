package org.example.servlet.mapper.student;

import org.example.model.Student;
import org.example.servlet.dto.student.StudentDto;
import org.mapstruct.Mapper;

@Mapper
public interface StudentDtoMapper {

    Student toEntity(StudentDto dto);

    StudentDto toDto(Student student);
}
