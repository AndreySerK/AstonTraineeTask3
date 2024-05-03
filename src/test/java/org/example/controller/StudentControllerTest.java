package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.dto.student.StudentDto;
import org.example.controller.mapper.student.StudentDtoMapper;
import org.example.model.Student;
import org.example.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService service;
    @Mock
    private StudentDtoMapper studentDtoMapper;

    @InjectMocks
    private StudentController controller;

    private MockMvc mockMvc;
    private StudentDto dto;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(controller).build();
        dto = new StudentDto(
                "Ivan",
                "Ivanov",
                20,
                "Moscow",
                1);
    }


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() throws Exception {
        when(service.findById(1)).thenReturn(new Student());
        when(studentDtoMapper.toDto(any(Student.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/get/{id}", 1))
                .andExpect(jsonPath("$.firstName").value("Ivan"));
        verify(service, times(1)).findById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() throws Exception {
        Student student = new Student();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            students.add(student);
        }
        when(studentDtoMapper.toDto(any(Student.class))).thenReturn(dto);
        when(service.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/all"))
                .andExpect(jsonPath("$", hasSize(5)));
        verify(service, times(1)).findAll();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/delete/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service, times(1)).deleteById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() throws Exception {
        Student student = studentDtoMapper.toEntity(dto);
        lenient().when(studentDtoMapper.toEntity(dto)).thenReturn(student);
        String jsonDto = new Gson().toJson(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/student/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(service, times(1)).save(student);
    }
}
