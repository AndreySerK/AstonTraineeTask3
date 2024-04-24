package org.example.service.impl;

import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    public Student findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id = " + id + " not found"));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }
}
