package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Student;
import org.example.service.StudentService;
import org.example.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;

class StudentServletTest extends Mockito {
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private StudentService service = mock(StudentServiceImpl.class);
    private StudentServlet servlet = new StudentServlet(service);


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/student/get");
        when(request.getParameter("id")).thenReturn("2");
        when(response.getWriter()).thenReturn(writer);
        when(service.findById(2)).thenReturn(new Student());

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getParameter("id");
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/student/all");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/student/delete");
        when(request.getParameter("id")).thenReturn("1");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getParameter("id");
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() throws IOException, ServletException {
        Student student = new Student();
        student.setFirstName("Test");
        student.setSecondName("Test");
        student.setAge(19);
        student.setFrom("Test");
        student.setUniversityId(2);
        String json = new Gson().toJson(student);
        BufferedReader reader = new BufferedReader(new StringReader(json));
        PrintWriter writer = new PrintWriter(new StringWriter());
        when(request.getServletPath()).thenReturn("/student/save");
        when(request.getReader()).thenReturn(reader);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getReader();
        verify(response,times(1)).getWriter();
    }
}
