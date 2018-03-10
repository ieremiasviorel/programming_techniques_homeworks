package model;

public class Ord {

	private int id;
	private int productId;
	private int customerId;
	private int quantity;
	
	public Ord(int id, int productId, int customerId, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.customerId = customerId;
		this.quantity = quantity;
	}
	
	public Ord() {
		new Ord(0, 0, 0, 0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Order " + id + " [ product=" + productId + " | " + " customer=" + customerId + " | quantity=" + quantity + " ]";
	}
}