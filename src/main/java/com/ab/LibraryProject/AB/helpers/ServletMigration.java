package com.ab.LibraryProject.AB.helpers;

import com.ab.LibraryProject.AB.constant.Path;
import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.enums.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletMigration {

    public static void changeServlet(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher userDispatcher =
                request.getRequestDispatcher(path);
        userDispatcher.forward(request, response);
    }

    public static Role checkPermission(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(context.getAttribute("Rola") == null){
            changeServlet(Path.pathToLoginPage, request, response);
        }
        return context.getAttribute("Rola").equals("ADMIN") ? Role.ADMIN : Role.USER;
    }
}
