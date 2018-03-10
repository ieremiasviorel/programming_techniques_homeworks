package presentation.views;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.Customer;
import model.Ord;
import model.Product;

public class ReceiptCreator {

	public static void createReceipt(Product p, Customer c, Ord o) {
		
		PrintWriter printer = null;
		try {
			printer = new PrintWriter("Order no." + o.getId() + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		printer.println("Order no." + o.getId());
		printer.println("----------------------------------------");
		printer.println("Client");
		printer.println("Name            : " + c.getName());
		printer.println("Address         : " +  c.getAddress());
		printer.println("Phone           : " + c.getPhone());
		printer.println("----------------------------------------");
		printer.println("Product");
		printer.println("Name            : " + p.getName());
		printer.println("Description     : " +  p.getDescription());
		printer.println("Unit Price      : " + p.getUnitPrice());
		printer.println("----------------------------------------");
		printer.println("Ordered units   : " + o.getQuantity());
		printer.println("----------------------------------------");
		printer.println("TOTAL           : " + ((int)(p.getUnitPrice() * o.getQuantity() + 0.4) * 100) / 100);
		printer.flush();
		printer.close();
	}
}