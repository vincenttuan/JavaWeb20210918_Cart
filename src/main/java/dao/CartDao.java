package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import entity.Order;
import entity.Product;

public class CartDao extends BaseDao {
	
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
		Product product = null;
		String sql = "SELECT id, name, qty, price FROM products WHERE id = ?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int pid = rs.getInt("id");
				String name = rs.getString("name");
				int qty = rs.getInt("qty");
				int price = rs.getInt("price");
				product = new Product(pid, name, qty, price);
			}
		} catch(Exception e) {
			e.printStackTrace(System.out);
		}
		return product;
	}
	
	// 查詢使用者訂單紀錄
	public List<Order> queryOrdersByUserId(Integer userId) {
		List<Order> orders = new ArrayList<Order>();
		String sql = "SELECT id, user_id, product_id, ts FROM orders WHERE user_id = ?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int product_id = rs.getInt("product_id");
				Date ts = rs.getDate("ts");
				Order order = new Order(id, user_id, product_id);
				orders.add(order);
			}
			
		} catch(Exception e) {
			e.printStackTrace(System.out);
		}
		
		return orders;
	}
	
	public void addOrder(Integer userId, String[] data) {
		if(data != null) {
			String sql = "INSERT INTO orders(user_id, product_id) VALUES(?, ?)";
			try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
				pstmt.clearBatch();
				for(String d : data) {
					Order order = new Order(0, userId, Integer.parseInt(d));
					pstmt.setInt(1, order.getUserId());
					pstmt.setInt(2, order.getProductId());
					pstmt.addBatch();
				}
				int[] rows = pstmt.executeBatch();
				System.out.println("rows: " + Arrays.toString(rows));
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
	}
	
}
