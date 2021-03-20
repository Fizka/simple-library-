package com.ab.LibraryProject.AB.servlets;

import com.ab.LibraryProject.AB.constant.Path;
import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.enums.Role;
import com.ab.LibraryProject.AB.helpers.ServletMigration;
import com.ab.LibraryProject.AB.helpers.PageWrapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/signIn", name = "signIn")
public class LoginServlet extends HttpServlet {

    private final String parameterName = "login";
    private final String adminLogin = "admin";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(parameterName);
        if (adminLogin.equals(login)) {
            ServletMigration.changeServlet(Path.pathToAdminLoginPanel, request, response);
        } else if (login == null) {
            setLoginPage(response);
        } else {
            ServletMigration.changeServlet(Path.pathToUserLoginPanel, request, response);
        }
    }

    private void setLoginPage(HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();
        PageWrapper.pageSetter(context, response, Page.LOGIN, Role.USER);
    }
}

