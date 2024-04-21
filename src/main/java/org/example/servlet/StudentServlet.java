package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.example.model.Student;
import org.example.service.StudentService;
import org.example.service.impl.StudentServiceImpl;
import org.example.servlet.dto.student.StudentDto;
import org.example.servlet.mapper.student.StudentDtoMapper;
import org.mapstruct.factory.Mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns =
        {"/student/get", "/student/all","/student/delete","/student/save"})
public class StudentServlet extends HttpServlet {
    
    private StudentService service;
    private StudentDtoMapper mapper = Mappers.getMapper(StudentDtoMapper.class);

    public StudentServlet(StudentService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/student/get" -> getById(req,resp);
            case "/student/all" -> getAll(req,resp);
            case "/student/delete" -> deleteById(req,resp);
            default -> printError(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if (action.equals("/student/save")) {
            String requestBody = IOUtils.toString(req.getReader());
            StudentDto dto = new Gson().fromJson(requestBody, StudentDto.class);
            service.save(mapper.toEntity(dto));
            printResult("Object saved", resp);
        } else {
            printResult("Wrong url", resp);
        }
    }

    private void getById (HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));// Our Id from request
        Student byId = service.findById(id);
        StudentDto dto = mapper.toDto(byId);
        String dtoJsonString = new Gson().toJson(dto);
        printResult(dtoJsonString,resp);
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) {
        List<StudentDto> students = new ArrayList<>();
        service.findAll().forEach(c -> students.add(mapper.toDto(c)));
        String dtoJsonString = new Gson().toJson(students);
        printResult(dtoJsonString,resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));// Our Id from request
        Boolean result = service.deleteById(id);
        printResult(result.toString(),resp);
    }

    private void printResult (String result,  HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printError (HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            resp.setCharacterEncoding("UTF-8");
            out.print("Wrong url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
