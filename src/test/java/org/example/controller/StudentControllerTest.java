package org.example.controller;

import org.example.config.AppConfig;
import org.example.controller.dto.student.StudentDto;
import org.example.model.Student;
import org.example.service.StudentService;
import org.example.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
class StudentControllerTest extends Mockito {
    
    private StudentService service = mock(StudentServiceImpl.class);

    @Autowired
    private StudentController controller;


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() {
       
        when(service.findById(2)).thenReturn(new Student());

        controller.getStudentById(2);

        verify(controller,times(1)).getStudentById(2);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() {

        controller.getAllStudents();

        verify(controller,times(1)).getAllStudents();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() {

        controller.deleteById(1);

        verify(controller,times(1)).deleteById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() {
        StudentDto dto = new StudentDto();
        dto.setFirstName("Test");
        dto.setSecondName("Test");
        dto.setAge(19);
        dto.setFrom("Test");
        dto.setUniversityId(2);

        controller.save(dto);

        verify(controller,times(1)).save(dto);
    }
}
