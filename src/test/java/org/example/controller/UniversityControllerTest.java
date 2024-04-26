package org.example.controller;

import jakarta.servlet.ServletException;
import org.example.config.AppConfig;
import org.example.controller.dto.university.IncomingUniversityDto;
import org.example.model.University;
import org.example.service.UniversityService;
import org.example.service.impl.UniversityServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class UniversityControllerTest extends Mockito {


    private final UniversityService service = mock(UniversityServiceImpl.class);

    @Autowired
    private UniversityController controller;


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() {

        when(service.findById(2)).thenReturn(new University());

        controller.getUniversityById(2);

        verify(controller, times(1)).getUniversityById(2);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() {

        controller.getAllUniversitys();

        verify(controller, times(1)).getAllUniversitys();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() {

        controller.deleteById(1);

        verify(controller, times(1)).deleteById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() {
        IncomingUniversityDto dto = new IncomingUniversityDto();
        dto.setName("Test");
        dto.setCity("Test");
        dto.setCountry("Test");


        controller.save(dto);

        verify(controller, times(1)).save(dto);
    }
}
