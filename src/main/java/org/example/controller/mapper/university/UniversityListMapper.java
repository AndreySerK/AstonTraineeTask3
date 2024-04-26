package org.example.controller.mapper.university;

import org.example.model.University;
import org.example.controller.dto.university.UniversityDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = UniversityDtoMapper.class)
public interface UniversityListMapper {

    List<UniversityDto> toDtoList(List<University> universities);
}
