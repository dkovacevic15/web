package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread implements Runnable {

	private Server server;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;

	public ServerThread(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			String request = input.readLine();
			String response;

			response = getResponse(request);
			output.println(response);
			output.flush();
			output.close();
			input.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// client.close();

	}

	private String getResponse(String request) {
		System.out.println(request);

		String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
		// String message = command.substring(command.indexOf("poljeForme=")+11,
		// command.indexOf("HTTP")-1);

		String body = null;

		System.out.println("\n\n\n");
		if (request.contains("username")) {
			String username = request.substring(request.indexOf("username=")+"username=".length(), request.lastIndexOf(' '));
			System.out.println("Username to be added: " + username);
			body = add(username);
			System.out.println("List of usernames: " + server.getUsernames().toString());
		} else if (request.contains("searcharg")) {
			String searcharg = request.substring(request.indexOf("searcharg=")+"searcharg=".length(), request.indexOf("HTTP/"));
			System.out.println("Searching for: " + searcharg);
			body = search(searcharg.trim());
		}


		response += "<html><head><title>Response</title></head>\n";
		response += "<body>" + body + "\n";
		response += "</body></html>";

		System.out.println("HTTP odgovor:");
		System.out.println(response);

		return response;
	}

	private String add(String username) {
		if (server.addUsername(username)) {
			return "<h1>Username successfully added!</h1>";
		} else {
			return "<h1>Error: Username already exists</h1>";
		}
	}
	private String search(String searcharg) {
		StringBuilder builder = new StringBuilder();
		boolean found = false;

		builder.append("<h1>Search results:</h1>\n");
		builder.append("<br><table>\n");


		searcharg = searcharg.replace(' ', '+');
		for (String username : server.getUsernames()) {
			if (username.contains(searcharg)) {
				found = true;
				builder.append("<tr><td>");
				builder.append(username);
				builder.append("</td></tr>");
			}
		}

		if (!found) {
			builder.append("<tr><td>");
			builder.append("No results found");
			builder.append("</td></tr>");
		}
		builder.append("</table>");
		return builder.toString();
	}

}
