package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final int PORT=8113;
	public List<String> usernames;
		
	public Server(int port) {
		usernames = new ArrayList<String>();
		try {
			@SuppressWarnings("resource") 
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server running!");
			System.out.println("Waiting for connections");
			Socket socket;
			while (true) {
				socket = ss.accept();
				System.out.println("Client connected: " + ss.getInetAddress() + ":" + ss.getLocalPort());
				(new Thread(new ServerThread(socket, this))).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean addUsername(String name) {
		if (!usernames.contains(name)) {
			usernames.add(name);
			return true;
		} else {
			return false;
		}

	}
	
	public synchronized List<String> getUsernames() {
		return usernames;
	}
	
	public static void main(String[] args) {
		new Server(PORT);
	}
}
