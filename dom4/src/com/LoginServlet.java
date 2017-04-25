package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PrintWriter output;
	
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		output = response.getWriter();
		
		printStaticAboveTable();
		
		for (User user : Users.getUsers()) {
			output.println("<tr>");
			output.println("<td>" + user.getUsername() + "</td>");
			output.println("<td>" + user.getPassword() + "</td>");
			output.println("</tr>");
		}
		
		printStaticBelowTable();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean added = Users.addUser(new User(username, password));
		
		response.setContentType("text/html");
		output = response.getWriter();
		printPostResponse(added);
	}

	private void printPostResponse(boolean added) {
		output.println("<!DOCTYPE html>");
		output.println("<html>");
		output.println("");
		output.println("<head>");
		output.println("<title>POST_RESPONSE</title>");
		output.println("</head>");
		output.println("");
		output.println("<body>");
		output.println("<p>");
		output.println("<b>");
		
		if (added) {
			output.println("User successfully added!");
		} else {
			output.println("User already exists!");
		}
		output.println("</b>");
		output.println("</p>");
		output.println("<p><a href=\"http://localhost:8080/Domaci/LoginServlet\">View user list</a>");
		output.println("</body>");
		output.println("");
		output.println("</html>");
		
	}

	private void printStaticAboveTable() {
		output.println("<!DOCTYPE html>");
		output.println("<html>");
		output.println("");
		output.println("<head>");
		output.println("<title>GET</title>");
		output.println("<style>");
		output.println("table {");
		output.println("font-family: arial, sans-serif;");
		output.println("border-collapse: collapse;");
		output.println("width: 100%;");
		output.println("}");
		output.println("");
		output.println("td,");
		output.println("th {");
		output.println("border: 1px solid #dddddd;");
		output.println("text-align: left;");
		output.println("padding: 8px;");
		output.println("}");
		output.println("</style>");
		output.println("</head>");
		output.println("");
		output.println("<body>");
		output.println("<table>");
		output.println("<tr>");
		output.println("<th>Username</th>");
		output.println("<th>Password:</th>");
		output.println("</tr>");
	}
	
	private void printStaticBelowTable() {
		output.println("</table>");
		output.println("</body>");
		output.println("");
		output.println("</html>");
	}
	
	
}