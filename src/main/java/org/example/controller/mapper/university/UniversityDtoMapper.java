package org.example.controller.mapper.university;

import org.example.model.University;
import org.example.controller.dto.university.IncomingUniversityDto;
import org.example.controller.dto.university.UniversityDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UniversityDtoMapper {

    University toEntity(IncomingUniversityDto dto);

    UniversityDto toDto(University university);
}
