package org.example.controller;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.example.model.Course;
import org.example.service.CourseService;
import org.example.controller.dto.course.CourseDto;
import org.example.controller.dto.course.IncomingCourseDto;
import org.example.controller.mapper.course.CourseDtoMapper;
import org.example.controller.mapper.student.StudentListMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.controller.utils.ServletUtils.printError;
import static org.example.controller.utils.ServletUtils.printResult;


@RestController("/course")
public class CourseController {

    private final CourseService service;
    private final CourseDtoMapper courseDtoMapper;
    private final StudentListMapper studentListMapper;

    public CourseController(CourseService service, CourseDtoMapper courseDtoMapper, StudentListMapper studentListMapper) {
        this.service = service;
        this.courseDtoMapper = courseDtoMapper;
        this.studentListMapper = studentListMapper;
    }

    @GetMapping("/get/{id}")
    protected CourseDto getCourseById(@PathVariable Integer id) {
        Course byId = service.findById(id);
        CourseDto dto = courseDtoMapper.toDto(byId);
        dto.setStudents(studentListMapper.toDtoList(byId.getStudents().stream().toList()));
        return dto;
    }

    @GetMapping("/all")
    protected List<CourseDto> getAllCourses() {
        List<CourseDto> courses = new ArrayList<>();
        service.findAll().forEach(c -> courses.add(courseDtoMapper.toDto(c)));
        return courses;
    }

    @DeleteMapping("/delete/{id}")
    protected ResponseEntity<?> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    protected ResponseEntity<?> save(@RequestBody IncomingCourseDto dto) {
        service.save(courseDtoMapper.toEntity(dto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
