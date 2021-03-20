package com.ab.LibraryProject.AB.helpers;

import com.ab.LibraryProject.AB.constant.Files;
import com.ab.LibraryProject.AB.constant.Path;
import com.ab.LibraryProject.AB.enums.Page;
import com.ab.LibraryProject.AB.enums.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PageGetter {

    public static String getTemplate(ServletContext context, String pageStructure, Page page, Role role) throws IOException {
        if (page.equals(Page.LIBRARY)) {
            return PageWrapper.setBooksList(pageStructure, role, context);
        } else if (page.equals(Page.LOGINERROR)) {
            return PageWrapper.setPage(pageStructure, Files.loginErrorTemplate, context);
        } else if (page.equals(Page.LOGIN)) {
            return PageWrapper.setPage(pageStructure, Files.loginTemplate, context);
        } else if (page.equals(Page.ADMINERROR)) {
            return PageWrapper.setPage(pageStructure, Files.bookErrorTemplate, context);
        } else {
            return PageWrapper.setPage(pageStructure, Files.status404Template, context);
        }
    }

    public static String getPage(String file, ServletContext context) throws IOException {
        InputStream pageFile = context.getResourceAsStream(Path.pathToHTMLFiles + file);
        StringBuffer pageText = new StringBuffer("");
        if (pageFile != null) {
            InputStreamReader isr = new InputStreamReader(pageFile, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (line != null) {
                pageText.append(line).append("\n");
                line = reader.readLine();
            }
        }
        return pageText.toString();
    }

}
