package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Search")
public class SearchPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public SearchPageServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter output = response.getWriter();
		
		output.println("<html>");
		output.println("");
		output.println("<head>");
		output.println("<title>Search</title>");
		output.println("</head>");
		output.println("");
		output.println("<body>");
		output.println("<form method=\"GET\" action=\"http://localhost:8080/Domaci/SearchServlet\">");
		output.println("<table>");
		output.println("<tr>");
		output.println("<td>Search for a user:</td>");
		output.println("</tr>");
		output.println("<tr>");
		output.println("<td><input type=\"text\" name=\"find\"></td>");
		output.println("</tr>");
		output.println("</table>");
		output.println("<input type=\"submit\" value=\"Search\">");
		output.println("</form>");
		output.println("</body>");
		output.println("");
		output.println("</html>");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
