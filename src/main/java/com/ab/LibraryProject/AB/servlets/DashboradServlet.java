package com.ab.LibraryProject.AB.servlets;

import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.helpers.PageWrapper;
import com.ab.LibraryProject.AB.helpers.ServletMigration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/dashboard", name = "dashboard")
public class DashboradServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        PageWrapper.pageSetter(context, response, Page.LIBRARY, ServletMigration.checkPermission(context, request, response));
    }
}
