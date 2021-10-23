package entity;

public class Product {
	
	private Integer id;
	private String name;
	private Integer qty;
	private Integer price;
	
	public Product() {
		
	}
	
	public Product(Integer id, String name, Integer qty, Integer price) {
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	
}
