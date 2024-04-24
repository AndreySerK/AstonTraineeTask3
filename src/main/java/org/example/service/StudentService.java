package org.example.service;

import org.example.model.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService extends SimpleService<Student,Integer> {
}
