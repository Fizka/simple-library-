package com.ab.LibraryProject.AB.servlets;

import com.ab.LibraryProject.AB.constant.Path;
import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.enums.Role;
import com.ab.LibraryProject.AB.helpers.ServletMigration;
import com.ab.LibraryProject.AB.helpers.PageWrapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/userSignIn")
public class UserLoginServlet extends javax.servlet.http.HttpServlet {

    private HashMap<String, String> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = new HashMap<>();
        users.put("user", "user");
        users.put("user1", "user1");
        users.put("user2", "user2");
        users.put("user3", "user3");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        if (login != null && pass != null) {
            String userLogin = checkUser(login, pass);
            if (userLogin != null) {
                context.setAttribute("login", userLogin);
                context.setAttribute("Rola", "USER");
                ServletMigration.changeServlet(Path.pathToDashboard, request, response);
            }
        }
        PageWrapper.pageSetter(context, response, Page.LOGINERROR, Role.USER);
    }

    private String checkUser(String login, String pass) {
        for (Map.Entry<String, String> userU : users.entrySet()) {
            if (userU.getKey().equals(login) && userU.getValue().equals(pass))
                return login;
        }
        return null;
    }

}
