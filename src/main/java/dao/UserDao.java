package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao extends BaseDao {
	
	private List<User> queryUsers() {
		List<User> users = new ArrayList<>();	
		String sql = "SELECT id, name, password FROM users";
		try(Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			while(rs.next()) { // 逐筆分析
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				User user = new User(null, name, password);
				users.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace(System.out);
		}
		return users;
	}
	
	public User loginCheck(String name, String password) {
		return queryUsers().stream()
				.filter(u -> u.getName().equals(name) && u.getPassword().equals(password))
				.findFirst()
				.get();
	}
	
	
}
