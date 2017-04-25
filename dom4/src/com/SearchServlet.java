package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PrintWriter output;
	
    public SearchServlet() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		output = response.getWriter();
		
		if (request.getParameter("find") != null) {
			printSearchResponse(request.getParameter("find"));
		} else if (request.getParameter("remove") != null) {
			printRemoveResponse(request.getParameter("remove"));
		} else {
			printErrorResponse();
		}
	}

	private void printSearchResponse(String usernameToFind) {
		printStaticAboveTable();
		
		List<User> users = Users.getUsers();
		for (User user : users) {
			if (user.getUsername().contains(usernameToFind)) {
				output.println("<tr>");
				output.println("<td>" + user.getUsername() + "</td>");
				output.println("<td>" + user.getPassword() + "</td>");
				output.println("<td><a href=\"http://localhost:8080/Domaci/SearchServlet?remove=" + user.getUsername() + "\">Remove</a></td>");
				output.println("</tr>");
			}
		}
		
		printStaticBelowTable();
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
		output.println("<th>Delete</th>");
		output.println("</tr>");
	}
	
	private void printStaticBelowTable() {
		output.println("</table>");
		output.println("</body>");
		output.println("");
		output.println("</html>");
	}
	
	private void printRemoveResponse(String usernameToRemove) {
		boolean removed = Users.removeUser(usernameToRemove);
		
		output.println("<!DOCTYPE html>");
		output.println("<html>");
		output.println("");
		output.println("<head>");
		output.println("<title>Remove user</title>");
		output.println("</head>");
		output.println("");
		output.println("<body>");
		output.println("<p>");
		
		if (removed) {
			output.println("User was successfully removed!");
		} else {
			output.println("ERROR: user does not exist");
		}
		
		output.println("</p>");
		output.println("<p><a href=\"http://localhost:8080/Domaci/LoginServlet\">View user list</a>");
		output.println("</body>");
		output.println("");
		output.println("</html>");

	}
	
	private void printErrorResponse() {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
