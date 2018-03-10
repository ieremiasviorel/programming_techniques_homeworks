package bll;

import dao.ProductDAO;
import model.Product;

public class ProductBLL extends AbstractBLL<Product> {
	
	public ProductBLL() {
		super(new ProductDAO());
	}
}