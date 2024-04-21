package org.example.service.impl;

import org.example.db.impl.ConnectionManagerImpl;
import org.example.model.University;
import org.example.repository.UniversityRepository;
import org.example.repository.impl.UniversityRepositoryImpl;
import org.example.service.UniversityService;

import java.util.List;

public class UniversityServiceImpl implements UniversityService {

    private UniversityRepository repository = new UniversityRepositoryImpl(new ConnectionManagerImpl());

    @Override
    public void save(University obj) {
         repository.save(obj);
    }

    @Override
    public University findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public List<University> findAll() {
        return repository.findAll();
    }
}
