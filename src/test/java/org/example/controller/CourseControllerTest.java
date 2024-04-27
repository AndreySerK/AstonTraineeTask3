package org.example.controller;

import org.example.config.AppConfig;
import org.example.controller.dto.course.IncomingCourseDto;
import org.example.model.Course;
import org.example.service.CourseService;
import org.example.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class CourseControllerTest extends Mockito {

    private final CourseService service = mock(CourseServiceImpl.class);

    @Autowired
    private CourseController controller;


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() {

        when(service.findById(2)).thenReturn(new Course());

        controller.getCourseById(2);

        verify(controller, times(1)).getCourseById(2);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() {
        when(service.findAll()).thenReturn(new ArrayList<>());

        controller.getAllCourses();

        verify(controller, times(1)).getAllCourses();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() {

        controller.deleteById(1);

        verify(controller, times(1)).deleteById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() {

        IncomingCourseDto dto = new IncomingCourseDto();
        dto.setCourseName("Physics");
        dto.setStudyYear(2015);
        dto.setUniversityId(2);

        controller.save(dto);

        verify(controller, times(1)).save(dto);
    }
}