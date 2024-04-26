package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.Course;
import org.example.repository.CourseRepository;
import org.example.service.CourseService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
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
