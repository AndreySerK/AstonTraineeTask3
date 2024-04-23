package org.example.controller.mapper.university;

import org.example.model.University;
import org.example.controller.dto.university.UniversityDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = UniversityDtoMapper.class)
public interface UniversityListMapper {

    List<UniversityDto> toDtoList(List<University> universities);
}
