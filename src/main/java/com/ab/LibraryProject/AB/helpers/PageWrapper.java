package com.ab.LibraryProject.AB.helpers;

import com.ab.LibraryProject.AB.constant.Files;
import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.enums.Role;
import com.ab.LibraryProject.AB.model.Library;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class PageWrapper {

    final static String pageContent = "[[PAGECONTENT]]";

    public static String setPage(String szablon,
                                 String plik, ServletContext context) throws IOException {
        return szablon.replace(pageContent, PageGetter.getPage(plik, context));
    }

    private static String libraryContent(Role role, ServletContext context) throws IOException {
        StringBuffer pageText = new StringBuffer("");
        if (role.equals(Role.ADMIN)) {
            pageText.append(" <form action=\"admin?activity=remove\" method=\"get\" name=\"bookForm\">").append("\n")
                    .append("<input type='hidden' name=\"activity\" value=\"remove\" />");
            Library.books.forEach(book -> {
                pageText.append("<input type=\"checkbox\" name=\"" + book.getTitle() + "\"" + "<p class=\"book\"> Autor: " +
                        book.getAuthor() + " Tytuł: " + book.getTitle() + " Rok: " + book.getYear() + "</p>").append("\n");
            });
            pageText.append("<button type=\"submit\" class=\"button-submit-left\"> Usuń </button>").append("</form>");
            pageText.append(PageGetter.getPage(Files.bookFormsTemplate, context));
        } else {
            Library.books.forEach(book -> {
                pageText.append("<p class=\"book\"> Autor: " + book.getAuthor() + " Tytuł: " + book.getTitle() +
                        " Rok: " + book.getYear() + "</p>").append("\n");
            });
        }
        return pageText.toString();
    }

    public static String setBooksList(String pageStructure, Role role, ServletContext context) throws IOException {
        return pageStructure.replace(pageContent, libraryContent(role, context));
    }

    public static void pageSetter(ServletContext context, HttpServletResponse response, Page page, Role role) throws IOException {
        String pageStructure = PageGetter.getPage(Files.mainTemplate, context);
        PageTools.printPage(response, PageGetter.getTemplate(context, pageStructure, page, role));
    }

}
