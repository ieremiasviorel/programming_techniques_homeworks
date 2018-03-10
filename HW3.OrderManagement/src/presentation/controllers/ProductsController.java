package presentation.controllers;

import bll.ProductBLL;
import model.Product;
import presentation.views.ProductsView;

public class ProductsController extends AbstractController<Product> {
	
	public ProductsController() {
		super(new ProductsView(), new ProductBLL());
	}
}