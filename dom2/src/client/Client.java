package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private BufferedReader input;
	private PrintWriter output;
	private Socket socket;


	public Client(String host, int port) {
		try {
			socket = new Socket(host, port);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			Scanner stdin = new Scanner(System.in);
			PrintStream stdout = System.out;
			String message;
			while (true) {
				message = stdin.nextLine();
				output.println(message);
				output.flush();
				stdout.println(input.readLine());
				if (message.equals("QUIT")) {
					break;
				}
			}
			input.close();
			output.close();
			socket.close();
			stdin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client("localhost", 9000);
	}
}
