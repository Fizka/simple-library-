package com.ab.LibraryProject.AB.servlets;

import com.ab.LibraryProject.AB.constant.Path;
import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.enums.Role;
import com.ab.LibraryProject.AB.helpers.PageWrapper;
import com.ab.LibraryProject.AB.helpers.ServletMigration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/adminSignIn")
public class AdminLoginServlet extends HttpServlet {

    private final String adminPass = "admin";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ServletContext context = getServletContext();
        String pass = request.getParameter("pass") == null ? "" : request.getParameter("pass");
        if (adminPass.equals(pass)) {
            context.setAttribute("login", adminPass);
            context.setAttribute("Rola", "ADMIN");
            ServletMigration.changeServlet(Path.pathToAdminPanel, request, response);
        }
        PageWrapper.pageSetter(context, response, Page.LOGINERROR, Role.ADMIN);
    }
}
