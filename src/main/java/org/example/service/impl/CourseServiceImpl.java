package org.example.service.impl;

import org.example.model.Course;
import org.example.repository.CourseRepository;
import org.example.service.CourseService;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;


    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Course course) {
        repository.save(course);
    }

    @Override
    public Course findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id = " + id + " not found"));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

}
