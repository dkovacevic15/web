package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomepageServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
		
		output.println("<!DOCTYPE html>");
		output.println("<html>");
		output.println("");
		output.println("<head>");
		output.println("<title>Homepage</title>");
		output.println("</head>");
		output.println("");
		output.println("<body>");
		output.println("<table>");
		output.println("<tr>");
		output.println("<td><a href=\"http://localhost:8080/Domaci/Login\" >Registration</a></td>");
		output.println("|");
		output.println("<td><a href=\"http://localhost:8080/Domaci/Search\" >Search</a></td>");
		output.println("</tr>");
		output.println("</table>");
		output.println("</body>");
		output.println("");
		output.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
