package org.example.controller;

import com.google.gson.Gson;
import org.example.controller.dto.course.CourseDto;
import org.example.controller.dto.course.IncomingCourseDto;
import org.example.controller.mapper.course.CourseDtoMapper;
import org.example.controller.mapper.student.StudentListMapper;
import org.example.model.Course;
import org.example.service.CourseService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseService service;
    @Mock
    private CourseDtoMapper courseDtoMapper;
    @Mock
    private StudentListMapper studentListMapper;

    @InjectMocks
    private CourseController controller;

    private MockMvc mockMvc;
    private CourseDto dto;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(controller).build();
        dto = new CourseDto(
                "Chemistry",
                2020,
                1,
                new ArrayList<>());
    }


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() throws Exception {
        when(service.findById(1)).thenReturn(new Course());
        when(courseDtoMapper.toDto(any(Course.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/course/get/{id}", 1))
                .andExpect(jsonPath("$.courseName").value("Chemistry"));
        verify(service, times(1)).findById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() throws Exception {
        Course course = new Course();
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            courses.add(course);
        }
        when(courseDtoMapper.toDto(any(Course.class))).thenReturn(dto);
        when(service.findAll()).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/course/all"))
                .andExpect(jsonPath("$", hasSize(5)));
        verify(service, times(1)).findAll();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/course/delete/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service, times(1)).deleteById(1);
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() throws Exception {

        IncomingCourseDto dto = new IncomingCourseDto();
        dto.setCourseName("Physics");
        dto.setStudyYear(2015);
        dto.setUniversityId(2);
        String jsonDto = new Gson().toJson(dto);
        Course course = courseDtoMapper.toEntity(dto);
        lenient().when(courseDtoMapper.toEntity(dto)).thenReturn(course);

        mockMvc.perform(MockMvcRequestBuilders.post("/course/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(service, times(1)).save(course);
    }
}