package dao;

import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {
	private static List<User> users;
	
	static {
		users = new ArrayList<>();
		users.add(new User(1, "admin", "1111"));
		users.add(new User(2, "john", "2222"));
		users.add(new User(3, "mary", "3333"));
	}
	
	public User loginCheck(String name, String password) {
		return users.stream()
				.filter(u -> u.getName().equals(name) && u.getPassword().equals(password))
				.findFirst()
				.get();
	}
	
	
}
