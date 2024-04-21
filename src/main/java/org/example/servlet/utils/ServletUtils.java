package org.example.servlet.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {

    public static void printResult (String result,  HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printError (HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            resp.setCharacterEncoding("UTF-8");
            out.print("Wrong url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
