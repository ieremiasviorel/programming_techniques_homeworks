package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Checkout implements Runnable {
	
	private Server cashier;
	private BlockingQueue<Client> clients;
	private int no;
	private boolean opened;
	private long startTime;
	private int clientsServed;
	private int serveTime;
	private int waitTime;
	
	public Checkout(int no, Server cashier, int maxNrClients) {
		this.cashier = cashier;
		this.clients = new ArrayBlockingQueue<Client>(maxNrClients);
		this.no = no;
		this.opened = true;
		this.startTime = System.currentTimeMillis();
		this.clientsServed = 0;
		this.serveTime = 0;
		this.waitTime = 0;
	}
	
	public int getNo() {
		return no;
	}
	
	public int compareTo(Checkout c) {
		
		if(this.clients.size() > c.clients.size()) {
			return -1;
		}
		else
		{
			if(this.clients.size() == c.clients.size()) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}
	
	public void serve() {
		while(opened) {
			try {
				if(!clients.isEmpty()) {
					Client c = clients.peek();
					cashier.serve(c);
					clientsServed++;
					
					System.out.println("Served : client " + (c.getId() + 1) + " cashier " + (this.no + 1));
					Shop.log = Shop.log + "Served : client " + (c.getId() + 1) + " cashier " + (this.no + 1) + "\n";
					c.setEndTime(System.currentTimeMillis() - this.startTime);
					c.setWaitTime(c.getEndTime() - c.getArrTime() - c.getServeTime() / (long)cashier.getEfficiency());
					
					this.serveTime += (long)(c.getServeTime() / cashier.getEfficiency());
					this.waitTime += c.getWaitTime();
					clients.take();
					
					System.out.println("Stats  : c" + (c.getId() + 1) + " : st " + c.getServeTime() / (long)cashier.getEfficiency() / 1000 + "; wt " + c.getWaitTime() / 1000);
					Shop.log = Shop.log + "Stats  : c" + (c.getId() + 1) + " : st " + c.getServeTime() / (long)cashier.getEfficiency() / 1000 + "; wt " + c.getWaitTime() / 1000 + "\n";
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getQueueSize() {
		return clients.size();
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void addClient(Client c) {
		try {
			c.setArrTime(System.currentTimeMillis() - this.startTime);
			clients.put(c);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Client removeClient() {
		try {
			return clients.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void run() {
		serve();
	}
	
	public boolean isOpened() {
		return opened;
	}
	
	public void openCheckout() {
		opened = true;
	}
	
	public void closeCheckout() {
		opened = false;
	}
	
	public long getWaitTime() {
		return waitTime;
	}
	
	public long getServeTime() {
		return serveTime;
	}
	
	public int getClientID(int index) {
		if(index <= clients.size()) {
			Client[] c = new Client[Shop.MAX_CLIENTS_QUEUE];
			clients.toArray(c);
			return c[index].getId() + 1;
		}
		else return -1;
	}
	
	public int getClientsServed() {
		return clientsServed;
	}
}