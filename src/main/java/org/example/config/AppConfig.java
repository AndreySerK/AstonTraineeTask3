package org.example.config;

import org.example.controller.mapper.course.CourseDtoMapper;
import org.example.controller.mapper.course.CourseDtoMapperImpl;
import org.example.controller.mapper.student.StudentDtoMapper;
import org.example.controller.mapper.student.StudentDtoMapperImpl;
import org.example.controller.mapper.student.StudentListMapper;
import org.example.controller.mapper.student.StudentListMapperImpl;
import org.example.controller.mapper.university.UniversityDtoMapper;
import org.example.controller.mapper.university.UniversityDtoMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.Collections;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan("org.example")
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new JsonMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    @Bean
    public CourseDtoMapper getCourseDtoMapper() {
        return new CourseDtoMapperImpl();
    }

    @Bean
    public StudentDtoMapper getStudentDtoMapper() {
        return new StudentDtoMapperImpl();
    }

    @Bean
    public StudentListMapper getStudentListMapper() {
        return new StudentListMapperImpl();
    }
    @Bean
    public UniversityDtoMapper getUniversityDtoMapper() {
        return new UniversityDtoMapperImpl();
    }
}
