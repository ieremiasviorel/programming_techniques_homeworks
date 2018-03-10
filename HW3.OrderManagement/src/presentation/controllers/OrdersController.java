package presentation.controllers;

import bll.OrderBLL;
import model.Ord;
import presentation.views.OrdersView;

public class OrdersController extends AbstractController<Ord> {
	
	public OrdersController() {
		super(new OrdersView(), new OrderBLL());
	}
}