package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import org.example.model.University;
import org.example.repository.UniversityRepository;
import org.example.service.UniversityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository repository;

    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(University obj) {
        repository.save(obj);
    }

    @Override
    public University findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University with id = " + id + " not found"));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<University> findAll() {
        return repository.findAll();
    }
}
