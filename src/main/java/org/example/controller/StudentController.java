package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;
import org.example.controller.dto.student.StudentDto;
import org.example.controller.mapper.student.StudentDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;



@RestController("/student")
public class StudentController {

    private final StudentService service;
    private final StudentDtoMapper studentDtoMapper;

    public StudentController(StudentService service, StudentDtoMapper studentDtoMapper) {
        this.service = service;
        this.studentDtoMapper = studentDtoMapper;
    }

    @GetMapping("/get/{id}")
    protected StudentDto getStudentById (@PathVariable Integer id) {
        Student byId = service.findById(id);
        return studentDtoMapper.toDto(byId);
    }

    @GetMapping("/all")
    protected List<StudentDto> getAllStudents ()  {
        List<StudentDto> students = new ArrayList<>();
        service.findAll().forEach(c -> students.add(studentDtoMapper.toDto(c)));
        return students;
    }

    @DeleteMapping("/delete/{id}")
    protected ResponseEntity<?> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    protected ResponseEntity<?> save(@RequestBody StudentDto dto) {
        service.save(studentDtoMapper.toEntity(dto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
