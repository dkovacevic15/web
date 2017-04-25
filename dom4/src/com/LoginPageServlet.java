package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginPageServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter output = response.getWriter();
		
		output.println("<!DOCTYPE html>");
		output.println("<html>");
		output.println("");
		output.println("<head>");
		output.println("<meta charset=\"ISO-8859-1\">");
		output.println("<title>Naslov sajta</title>");
		output.println("</head>");
		output.println("");
		output.println("<body>");
		output.println("<h1>Forma:</h1>");
		output.println("<form method=\"POST\" action=\"http://localhost:8080/Domaci/LoginServlet\">");
		output.println("<table>");
		output.println("<tr>");
		output.println("<td><label>User name:</label></td>");
		output.println("<td><input type=\"text\" name=\"username\"></td>");
		output.println("</tr>");
		output.println("<tr>");
		output.println("<td><label>Password:</label></td>");
		output.println("<td><input type=\"password\" name=\"password\"></td>");
		output.println("</tr>");
		output.println("");
		output.println("</table><input type=\"submit\" value=\"Submit\">");
		output.println("</form>");
		output.println("</body>");
		output.println("");
		output.println("</html>");

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
