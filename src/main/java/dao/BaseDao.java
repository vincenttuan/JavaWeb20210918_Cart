package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
	
	private static Connection conn;
	
	static {
		String url = "jdbc:mysql://localhost:3306/web?serverTimezone=UTC&characterEncoding=utf-8&useUnicode=true";
		String user = "root";
		String password = "12345678";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
