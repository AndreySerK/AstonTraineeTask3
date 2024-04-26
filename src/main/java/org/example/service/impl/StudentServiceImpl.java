package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
