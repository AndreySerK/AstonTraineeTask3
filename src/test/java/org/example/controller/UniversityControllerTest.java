package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.dto.student.StudentDto;
import org.example.controller.dto.university.IncomingUniversityDto;
import org.example.controller.dto.university.UniversityDto;
import org.example.controller.mapper.university.UniversityDtoMapper;
import org.example.model.Student;
import org.example.model.University;
import org.example.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class UniversityControllerTest extends Mockito {

    @Mock
    private UniversityService service;
    @Mock
    private UniversityDtoMapper universityDtoMapper;

    @InjectMocks
    private UniversityController controller;

    private MockMvc mockMvc;
    private UniversityDto dto;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(controller).build();
        dto = new UniversityDto(
                "Test",
                "Test",
                "Test",
                new ArrayList<>(),
                new ArrayList<>());
    }


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() throws Exception {
        when(service.findById(1)).thenReturn(new University());
        when(universityDtoMapper.toDto(any(University.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/university/get/{id}", 1))
                .andExpect(jsonPath("$.name").value("Test"));
        verify(service, times(1)).findById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() throws Exception {
        University university = new University();
        List<University> universities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            universities.add(university);
        }
        when(universityDtoMapper.toDto(any(University.class))).thenReturn(dto);
        when(service.findAll()).thenReturn(universities);

        mockMvc.perform(MockMvcRequestBuilders.get("/university/all"))
                .andExpect(jsonPath("$", hasSize(5)));
        verify(service, times(1)).findAll();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/university/delete/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service, times(1)).deleteById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() throws Exception {
        IncomingUniversityDto dto = new IncomingUniversityDto();
        University university = universityDtoMapper.toEntity(dto);
        dto.setName("Test");
        dto.setCity("Test");
        dto.setCountry("Test");
        lenient().when(universityDtoMapper.toEntity(dto)).thenReturn(university);
        String jsonDto = new Gson().toJson(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/university/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(service, times(1)).save(university);
    }
}
