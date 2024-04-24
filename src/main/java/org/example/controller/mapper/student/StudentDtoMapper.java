package org.example.controller.mapper.student;

import org.example.model.Student;
import org.example.controller.dto.student.StudentDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface StudentDtoMapper {

    Student toEntity(StudentDto dto);

    StudentDto toDto(Student student);
}
