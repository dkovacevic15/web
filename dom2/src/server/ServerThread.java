package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread implements Runnable{

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
		String command;
		String message;
		try {
			while (!(command = input.readLine()).equals("QUIT")) {
				System.out.println("Client from socket " + socket.toString() + " says: " + command);
				if (command.equals("LIST")) {
					message = list();
				} else if (command.startsWith("ADD")) {
					String username = command.substring(4).trim();
					message = add(username);
				} else {
					message = "Error: unrecognized command";
				}
				output.println(message);
				output.flush();
			}
			output.println("Bye-bye!");
			output.flush();
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private String list() {
		List<String> usernames = server.getUsernames();
		StringBuilder builder = new StringBuilder();
		
		builder.append("List of usernames:");
		for (String username : usernames) {
			builder.append(' ');
			builder.append(username);
		}
		return builder.toString();
	}

	private String add(String username) {
		if (server.getUsernames().add(username)) {
			return "Username successfully added!";
		} else {
			return "Error: Username already exists";
		}
	}
}
