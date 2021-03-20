package com.ab.LibraryProject.AB.servlets;

import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.enums.Role;
import com.ab.LibraryProject.AB.helpers.PageWrapper;
import com.ab.LibraryProject.AB.model.Book;
import com.ab.LibraryProject.AB.model.Library;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = "/admin", name = "admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String action = request.getParameter("activity") == null ? "" : request.getParameter("activity");
        if (action.equals("add")) {
            addBook(context, request, response);
        } else if (action.equals("remove")) {
            removeBook(request, context);
        }
        PageWrapper.pageSetter(context, response, Page.LIBRARY, Role.ADMIN);
    }

    private void addBook(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("title").equals("")) {
            getErrorPage(response, context);
        } else {
            Library.addBook(new Book(request.getParameter("title"), request.getParameter("author") == null ? "" : request.getParameter("author"),
                    request.getParameter("year").equals("") ? 0 : Integer.parseInt(request.getParameter("year"))), context);
        }
    }

    private void removeBook(HttpServletRequest request, ServletContext context) {
        List<String> parameterNamesList = Collections.list(request.getParameterNames());
        parameterNamesList.remove("activity");
        Library.removeBooks(parameterNamesList, context);
    }

    private void getErrorPage(HttpServletResponse response, ServletContext context) throws IOException {
        PageWrapper.pageSetter(context, response, Page.ADMINERROR, Role.ADMIN);
    }
}
