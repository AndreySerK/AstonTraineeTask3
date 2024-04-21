package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.example.model.University;
import org.example.service.UniversityService;
import org.example.service.impl.UniversityServiceImpl;
import org.example.servlet.dto.university.UniversityDto;
import org.example.servlet.dto.university.IncomingUniversityDto;
import org.example.servlet.mapper.course.CourseListMapper;
import org.example.servlet.mapper.university.UniversityDtoMapper;
import org.example.servlet.mapper.student.StudentListMapper;
import org.mapstruct.factory.Mappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.example.servlet.utils.ServletUtils.printError;
import static org.example.servlet.utils.ServletUtils.printResult;

@WebServlet(urlPatterns =
        {"/university/get", "/university/all","/university/delete","/university/save"})
public class UniversityServlet extends HttpServlet {

    private UniversityService service;
    private UniversityDtoMapper universityDtoMapper = Mappers.getMapper(UniversityDtoMapper.class);
    private StudentListMapper studentListMapper = Mappers.getMapper(StudentListMapper.class);
    private CourseListMapper courseListMapper = Mappers.getMapper(CourseListMapper.class);

    public UniversityServlet(UniversityService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/university/get" -> getById(req,resp);
            case "/university/all" -> getAll(req,resp);
            case "/university/delete" -> deleteById(req,resp);
            default -> printError(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if (action.equals("/university/save")) {
            String requestBody = IOUtils.toString(req.getReader());
            IncomingUniversityDto dto = new Gson().fromJson(requestBody, IncomingUniversityDto.class);
            service.save(universityDtoMapper.toEntity(dto));
            printResult("Object saved", resp);
        } else {
            printResult("Wrong url", resp);
        }
    }

    private void getById (HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));// Our Id from request
        University byId = service.findById(id);
        UniversityDto dto = universityDtoMapper.toDto(byId);
        dto.setStudents(studentListMapper.toDtoList(byId.getStudents()));
        dto.setCourses(courseListMapper.toDtoList(byId.getCourses()));
        String dtoJsonString = new Gson().toJson(dto);
        printResult(dtoJsonString,resp);
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) {
        List<UniversityDto> universities = new ArrayList<>();
        service.findAll().forEach(c -> universities.add(universityDtoMapper.toDto(c)));
        String dtoJsonString = new Gson().toJson(universities);
        printResult(dtoJsonString,resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));// Our Id from request
        Boolean result = service.deleteById(id);
        printResult(result.toString(),resp);
    }
}
