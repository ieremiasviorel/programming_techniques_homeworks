package model;

public class Client {

	private int id;
	private long arrTime;
	private long endTime;
	private long waitTime;
	private long serveTime;
	
	public Client(int id, int serveTime) {
		this.id = id;
		this.serveTime = serveTime;
		this.waitTime = 0;
		this.arrTime = 0;
		this.endTime = 0;
	}
	
	public long getArrTime() {
		return arrTime;
	}
	
	public long getEndTime() {
		return endTime;
	}
	
	public long getServeTime() {
		return serveTime;
	}
	
	public long getWaitTime() {
		return waitTime;
	}
	
	public int getId() {
		return id;
	}
	
	public void setArrTime(long arrTime) {
		this.arrTime = arrTime;
	}
	
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}
}