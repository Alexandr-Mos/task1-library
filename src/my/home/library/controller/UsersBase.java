package my.home.library.controller;

import java.util.ArrayList;

import my.home.library.model.User;

public class UsersBase {
	private ArrayList<User> usersBase;
	public static final String FILE_NAME = "users.txt";
	
	public UsersBase(ArrayList<User> usersBase) {
		this.usersBase = usersBase;
	}
	
	public User find(String email) {
		for (User u : usersBase) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
	}
	
	public void addUser(User user) {
		usersBase.add(user);
	}

	public ArrayList<User> getUsersList() {
		return usersBase;
	}

	public void setUsersList(ArrayList<User> usersBase) {
		this.usersBase = usersBase;
	}
	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (User u : usersBase) {
			sb.append(u.getEmail() + " ");
			sb.append(u.getPassword() + " ");
			sb.append(u.isAdmin() + "\n");
		}
		return sb.toString();
	}

}
