package com.ab.LibraryProject.AB.servlets;

import com.ab.LibraryProject.AB.constant.Path;
import com.ab.LibraryProject.AB.helpers.ServletMigration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "", name = "WelcomeServlet")
public class WelcomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String path = context.getAttribute("login") == "" ? Path.pathToLoginPage : Path.pathToDashboard;
        ServletMigration.changeServlet(path, request, response);
    }
}
