package org.example.service.impl;

import org.example.db.impl.ConnectionManagerImpl;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.example.repository.impl.StudentRepositoryImpl;
import org.example.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentRepository repository = new StudentRepositoryImpl(new ConnectionManagerImpl());
    @Override
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    public Student findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }
}
