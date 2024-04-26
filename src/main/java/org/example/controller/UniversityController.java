package org.example.controller;

import org.example.controller.dto.university.IncomingUniversityDto;
import org.example.model.University;
import org.example.service.UniversityService;
import org.example.controller.dto.university.UniversityDto;
import org.example.controller.mapper.university.UniversityDtoMapper;
import org.example.service.impl.UniversityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService service;
    private final UniversityDtoMapper universityDtoMapper;

    public UniversityController(UniversityService service, UniversityDtoMapper universityDtoMapper) {
        this.service = service;
        this.universityDtoMapper = universityDtoMapper;
    }

    @GetMapping("/get/{id}")
    protected UniversityDto getUniversityById(@PathVariable(name = "id") Integer id) {
        University byId = service.findById(id);
        return universityDtoMapper.toDto(byId);
    }

    @GetMapping("/all")
    protected List<UniversityDto> getAllUniversitys() {
        List<UniversityDto> universities = new ArrayList<>();
        service.findAll().forEach(c -> universities.add(universityDtoMapper.toDto(c)));
        return universities;
    }

    @DeleteMapping("/delete/{id}")
    protected ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    protected ResponseEntity<?> save(@RequestBody IncomingUniversityDto dto) {
        service.save(universityDtoMapper.toEntity(dto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
