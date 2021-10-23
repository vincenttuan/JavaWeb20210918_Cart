package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Order;
import entity.Product;

public class CartDao {
	
	private static List<Product> products;
	private static List<Order> orders;
	
	static {
		orders = new ArrayList<>();
		products = new ArrayList<>();
		products.add(new Product(1, "鉛筆", 10, 10));
		products.add(new Product(2, "橡皮", 20, 10));
		products.add(new Product(3, "墊板", 30, 20));
		products.add(new Product(4, "圓規", 40, 30));
		products.add(new Product(5, "彈珠", 50, 10));
	}
	
	// 查詢所有商品
	public List<Product> queryProducts() {
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
