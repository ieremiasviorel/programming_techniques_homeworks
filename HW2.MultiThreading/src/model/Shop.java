package model;

import java.util.ArrayList;
import java.util.List;

import factories.Factory;

public class Shop implements Runnable {
	
	public static final int MIN_SERVE_TIME = 4000;
	public static final int MAX_SERVE_TIME = 16000;
	public static final int MIN_ARRIVAL_TIME = 1000;
	public static final int MAX_ARRIVAL_TIME = 4000;
	public static final int NR_QUEUES = 7;
	public static final int MAX_CLIENTS_QUEUE = 10;
	
	private int minServeTime;
	private int maxServeTime;
	private int minArrivalTime;
	private int maxArrivalTime;
	private int nrQueues;
	private int simulationTime;
	private int maxNrClients;
	public static String log;
	
	private List<Checkout> checkouts;

	public Shop() {
		
		this.minServeTime = MIN_SERVE_TIME;
		this.maxServeTime = MAX_SERVE_TIME;
		this.minArrivalTime = MIN_ARRIVAL_TIME;
		this.maxArrivalTime = MAX_ARRIVAL_TIME;
		this.nrQueues = NR_QUEUES;
		this.simulationTime = 0;
		this.maxNrClients = 0;
		checkouts = new ArrayList<Checkout>(nrQueues);
		for(int i = 0; i < nrQueues; i++) {
			checkouts.add(i, new Checkout(i, Factory.generateServer(), MAX_CLIENTS_QUEUE));
		}
		for(int i = 0; i < NR_QUEUES; i++) {
		checkouts.get(i).closeCheckout();
		}
		log = "START\n";
	}
	
	public Checkout shortestQueue() {
		Checkout shortestQueue = checkouts.get(0);
		for(Checkout checkout : checkouts) {
			if(checkout.isOpened() && shortestQueue.compareTo(checkout) < 0) {
				shortestQueue = checkout;
			}
		}
		return shortestQueue;
	}
	
	public void addClient() {
		int count = 0;
		while(simulationTime > (System.currentTimeMillis() - checkouts.get(0).getStartTime())) {
			if(!allQueuesFull()) {
				try {
					Thread.sleep(Factory.generateWaitTime(minArrivalTime, maxArrivalTime));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Checkout queueToAddTo = shortestQueue();
				Client c = Factory.generateClient(count, minServeTime, maxServeTime);
				queueToAddTo.addClient(c);
				System.out.println("Arrived: client " + (count + 1) + " queue " + (queueToAddTo.getNo() + 1));
				log = log + "Arrived: client " + (count + 1) + " queue " + (queueToAddTo.getNo() + 1) + "\n";
				count++;
				
			}
		}
		log = log + "END\n";
	}
	
	public void openNewQueue() {
		for(int i = 0; i < nrQueues; i++) {
			if(checkouts.get(i).isOpened() == false) {
				checkouts.get(i).openCheckout();
				redistributeClients(i);
				break;
			}
		}
	}
	
	public boolean allQueuesFull() {
		for(int i = 0; i < nrQueues; i++) {
			if(checkouts.get(i).getQueueSize() != MAX_CLIENTS_QUEUE) {
				return false;
			}
		}
		return true;
	}
	
	public void redistributeClients(int index) {
		int averageNrClients = 0;
		int nrOpenedCheckouts = 0;
		for(Checkout c : checkouts) {
			if(c.isOpened()) {
				averageNrClients += c.getQueueSize();
				nrOpenedCheckouts++;
			}
		}
		averageNrClients /= (nrOpenedCheckouts + 1);
		
		for(Checkout c : checkouts) {
			if(c.isOpened()) {
				while(averageNrClients <= c.getQueueSize() && !c.equals(checkouts.get(index))) {
					Client cl = c.removeClient();
					checkouts.get(index).addClient(cl);
					System.out.println("Client " + cl.getId() + " moved from queue " + c.getNo() + " to queue " + index);
				}
			}
		}
	}
	
	public String getLog() {
		return log;
	}
	
	public int getSimTime() {
		return simulationTime;
	}
	
	public int nrOfCheckouts() {
		return checkouts.size();
	}
	
	public Checkout checkoutNr(int i) {
		return checkouts.get(i);
	}
	
	public int getMaxNrClients() {
		return maxNrClients;
	}
	
	public int getHour() {
		return (int)(System.currentTimeMillis() - checkouts.get(0).getStartTime());
	}
	
	public void run() {
		addClient();
	}
	
	public void setMinArrTime(int t) {
		this.minArrivalTime = t;
	}
	
	public void setMaxArrTime(int t) {
		this.maxArrivalTime = t;
	}
	
	public void setMinServeTime(int t) {
		this.minServeTime = t;
	}
	
	public void setMaxServeTime(int t) {
		this.maxServeTime = t;
	}
	
	public void setMaxNrClients(int t) {
		this.maxNrClients = t;
	}
	
	public void setSimulationTime(int t) {
		this.simulationTime = t;
	}
	
	public void setNrQueues(int t) {
		this.nrQueues = t;
		for(int i = 0; i < t; i++) {
			checkouts.get(i).openCheckout();;
		}
	}
	
	public int[][] clientsStatus() {
		int clients[][] = new int[NR_QUEUES][MAX_CLIENTS_QUEUE];
		for(int i = 0; i < NR_QUEUES; i++) {
			int size = checkouts.get(i).getQueueSize();
			for(int j = 0; j < size; j++) {
				clients[i][j] = checkouts.get(i).getClientID(j);
			}
			for(int j = size; j < MAX_CLIENTS_QUEUE; j++) {
				clients[i][j] = -1;
			}
		}
		return clients;
	}
	
	public int getTotNrClients() {
		int n = 0;
		for(int i = 0; i < NR_QUEUES; i++) {
			n += checkouts.get(i).getClientsServed();
		}
		return n;
	}
	
	public float getAvgServeTime() {
		int totServeTime = 0;
		for(int i = 0; i < NR_QUEUES; i++) {
			totServeTime += checkouts.get(i).getServeTime();
		}
		if(getTotNrClients() > 0) {
			return (int)(totServeTime / getTotNrClients() / (float)10) / (float)100;
		}
		else {
			return 0;
		}
	}
	
	public float getAvgWaitTime() {
		int totWaitTime = 0;
		for(int i = 0; i < NR_QUEUES; i++) {
			totWaitTime += checkouts.get(i).getWaitTime();
		}
		if(getTotNrClients() > 0) {
			return (int)(totWaitTime / getTotNrClients() / (float)10) / (float)100;
		}
		else {
			return 0;
		}
	}
	
	public int[] getNrClients() {
		int nrClients[] = new int[NR_QUEUES];
		for(int i = 0; i < NR_QUEUES; i++) {
			nrClients[i] = checkouts.get(i).getClientsServed();
		}
		return nrClients;
	}
	
	public int getNrClientsInstant() {
		int n = 0;
		for(int i = 0; i < nrQueues; i++) {
			n += checkouts.get(i).getQueueSize();
		}
		return n;
	}
}