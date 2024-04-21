package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.University;
import org.example.service.UniversityService;
import org.example.service.impl.UniversityServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;

class UniversityServletTest extends Mockito {

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private UniversityService service = mock(UniversityServiceImpl.class);
    private UniversityServlet servlet = new UniversityServlet(service);


    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getByIdTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/university/get");
        when(request.getParameter("id")).thenReturn("2");
        when(response.getWriter()).thenReturn(writer);
        when(service.findById(2)).thenReturn(new University());

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getParameter("id");
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_getAllTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/university/all");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_deleteByIdTest() throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getServletPath()).thenReturn("/university/delete");
        when(request.getParameter("id")).thenReturn("1");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getParameter("id");
        verify(response,times(1)).getWriter();
    }

    @Test
    void whenCorrectUrlIsPassed_thenCorrectMethodIsCalled_saveTest() throws IOException, ServletException {
        University university = new University();
        university.setName("Test");
        university.setCity("Test");
        university.setCountry("Test");
        String json = new Gson().toJson(university);
        BufferedReader reader = new BufferedReader(new StringReader(json));
        PrintWriter writer = new PrintWriter(new StringWriter());
        when(request.getServletPath()).thenReturn("/university/save");
        when(request.getReader()).thenReturn(reader);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);

        verify(request,times(1)).getServletPath();
        verify(request,times(1)).getReader();
        verify(response,times(1)).getWriter();
    }
}
