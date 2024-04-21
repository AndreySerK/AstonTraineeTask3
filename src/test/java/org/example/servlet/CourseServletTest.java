package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Course;
import org.example.service.CourseService;
import org.example.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;


class CourseServletTest extends Mockito {
    // mock HttpServletRequest & HttpServletResponse
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private CourseService service = mock(CourseServiceImpl.class);
    private CourseServlet servlet = new CourseServlet(service);


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/course/get");
        when(request.getParameter("id")).thenReturn("2");
        when(response.getWriter()).thenReturn(writer);
        when(service.findById(2)).thenReturn(new Course());

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getParameter("id");
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/course/all");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/course/delete");
        when(request.getParameter("id")).thenReturn("1");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getParameter("id");
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() throws IOException, ServletException {
        Course course = new Course();
        course.setCourseName("Physics");
        course.setStudyYear(2015);
        course.setUniversityId(2);
        String json = new Gson().toJson(course);
        BufferedReader reader = new BufferedReader(new StringReader(json));
        PrintWriter writer = new PrintWriter(new StringWriter());
        when(request.getServletPath()).thenReturn("/course/save");
        when(request.getReader()).thenReturn(reader);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getReader();
        verify(response,times(1)).getWriter();
    }
}