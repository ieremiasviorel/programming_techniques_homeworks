package bll;

import dao.OrderDAO;
import model.Ord;

public class OrderBLL extends AbstractBLL<Ord> {

	public OrderBLL() {
		super(new OrderDAO());
	}
}