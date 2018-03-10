package model;

public class Server {

	private double efficiency;
	
	public Server(double efficiency) {
		this.efficiency = efficiency;
	}
	
	public void serve(Client c) {
		
		try {
			Thread.sleep((long)(c.getServeTime()) / (long)this.efficiency);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public double getEfficiency() {
		return efficiency;
	}
}