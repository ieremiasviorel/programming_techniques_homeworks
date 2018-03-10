package model;

public class Product {
	
	private int id;
	private String name;
	private String description;
	private double unitPrice;
	private int unitsInStock;
	
	public Product(int id, String name, String description, double unitPrice, int unitsInStock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
	}
	
	public Product(int id, String name, double unitPrice, int unitsInStock) {
		new Product(id, name, "No description", unitPrice, unitsInStock);
	}
	
	public Product() {
		new Product(0, "Empty name", "Empty description", 0, 0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	
	@Override
	public String toString() {
		return "Product " + id + " [ name=" + name + " | " + " price=" + unitPrice + " | amount=" + unitsInStock + " ]";
	}
}