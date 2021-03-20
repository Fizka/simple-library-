package com.ab.LibraryProject.AB.servlets;

import com.ab.LibraryProject.AB.model.Library;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

@WebListener()
public class LibraryContextServletListener implements ServletContextListener,
        ServletContextAttributeListener {

    private Library lib;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        lib = new Library();
        ArrayList books = Library.books;
        sce.getServletContext().setAttribute("books", books);
    }
}
