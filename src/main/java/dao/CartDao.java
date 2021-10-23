package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Order;
import entity.Product;

public class CartDao extends BaseDao {
	
	private static List<Product> products;
	private static List<Order> orders;
	
	static {
		orders = new ArrayList<>();
	}
	
	// 查詢所有商品
	public List<Product> queryProducts() {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT id, name, qty, price FROM products";
		try(Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int qty = rs.getInt("qty");
				int price = rs.getInt("price");
				Product product = new Product(id, name, qty, price);
				products.add(product);
			}
			
		} catch(Exception e) {
			e.printStackTrace(System.out);
		}
		return products;
	}
	
	// 查詢單一商品
	public Product getPriductById(Integer id) {
		return products.stream().filter(p -> p.getId() == id).findFirst().get();
	}
	
	// 查詢使用者訂單紀錄
	public List<Order> queryOrdersByUserId(Integer userId) {
		return orders.stream().filter(o -> o.getUserId() == userId).toList();
	}
	
	public void addOrder(Integer userId, String[] data) {
		// 自編 order id
		Integer orderId = Math.abs((int)new Date().getTime());
		if(data != null) {
			for(String d : data) {
				Order order = new Order(orderId, userId, Integer.parseInt(d));
				orders.add(order);
			}
		}
	}
	
}
