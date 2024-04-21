package org.example.servlet.mapper.university;

import org.example.model.University;
import org.example.servlet.dto.university.IncomingUniversityDto;
import org.example.servlet.dto.university.UniversityDto;
import org.mapstruct.Mapper;

@Mapper
public interface UniversityDtoMapper {

    University toEntity(IncomingUniversityDto dto);

    UniversityDto toDto(University university);
}
