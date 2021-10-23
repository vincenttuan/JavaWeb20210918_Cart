package entity;

import java.util.Date;

public class Order {
	private Integer id; // 訂單編號
	private Integer userId; // 訂貨人 id
	private Integer productId; // 商品 id
	private Date ts; // 下單時間
	
	public Order() {
		
	}
	
	public Order(Integer id, Integer userId, Integer productId) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.ts = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	
}
