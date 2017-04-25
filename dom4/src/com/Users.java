package com;

import java.util.ArrayList;
import java.util.List;

public class Users {
	
	private static List<User> users = new ArrayList<User>();
	
	public static synchronized List<User> getUsers() {
		return users;
	}
	
	public static synchronized boolean addUser(User newUser) {
		boolean exists = false;
		for (User user : users) {
			if (user.equals(newUser)) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			users.add(newUser);
		}
		
		return !exists; // Ako ne postoji, dodat je
	}
	
	public static synchronized boolean removeUser(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				users.remove(user);
				return true;
			}
		}
		return false;
	}
}
