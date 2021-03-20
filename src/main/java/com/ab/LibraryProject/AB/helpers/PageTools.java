package com.ab.LibraryProject.AB.helpers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PageTools {

    public static void printPage(HttpServletResponse response, String pageStructure) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(pageStructure);
        out.close();
    }

}
