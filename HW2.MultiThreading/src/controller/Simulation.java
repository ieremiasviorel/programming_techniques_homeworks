package controller;

import java.util.ArrayList;
import java.util.List;

import model.Shop;
import view.MainWindow;

public class Simulation {
	
	public static void main(String[] args) {
		
		Shop shop = new Shop();
		Thread t = new Thread(shop);
		List<Thread> c = new ArrayList<Thread>(shop.nrOfCheckouts());
		for(int i = 0; i < shop.nrOfCheckouts(); i++) {
			c.add(i, new Thread(shop.checkoutNr(i)));
		}
		
		MainWindow mainWindow = new MainWindow();
		
		@SuppressWarnings("unused")
		MainController mainController = new MainController(shop, mainWindow, t, c);
		
		try {
			t.join();
			for(int i = 0; i < shop.nrOfCheckouts(); i++) {
				c.get(i).join();
			}
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}