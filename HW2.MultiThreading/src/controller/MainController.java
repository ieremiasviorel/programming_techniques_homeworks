package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import model.Shop;
import view.MainWindow;

public class MainController {
	
	
	private Shop model;
	private MainWindow view;
	private Thread shopThread;
	private List<Thread> queueThreads;
	private Timer refreshView;
	
	public MainController(Shop s, MainWindow v, Thread t, List<Thread> c) {
		this.model = s;
		this.view = v;
		this.shopThread = t;
		this.queueThreads = c;
		view.addStartBtnActionListener(new StartListener());
		view.addPauseBtnActionListener(new PauseListener());
		view.addResumeBtnActionListener(new ResumeListener());
		refreshView = new Timer(10, new TaskPerformer());
		
	}
	
	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int minArrivalTime = Integer.parseInt(view.getMinArrTimeText());
				model.setMinArrTime(minArrivalTime);
			}
			catch (NumberFormatException ex) {
				System.err.println("Number Format Exception case 1.");
			}
			
			try {
				int maxArrivalTime = Integer.parseInt(view.getMaxArrTimeText());
				model.setMaxArrTime(maxArrivalTime);
			}
			catch (NumberFormatException ex) {
				System.err.println("Number Format Exception case 2.");
			}
			
			try {
				int minServeTime = Integer.parseInt(view.getMinServeTimeText());
				model.setMinServeTime(minServeTime);
			}
			catch (NumberFormatException ex) {
				System.err.println("Number Format Exception case 3.");
			}
			
			try {
				int maxServeTime = Integer.parseInt(view.getMaxServeTimeText());
				model.setMaxServeTime(maxServeTime);
			}
			catch (NumberFormatException ex) {
				System.err.println("Number Format Exception case 4.");
			}
			
			try {
				int simTime = Integer.parseInt(view.getSimTime());
				model.setSimulationTime(simTime * 1000);
			}
			catch (NumberFormatException ex) {
				System.err.println("Numbet Format Exception case 5.");
			}
			
			try {
				int nrQueues = Integer.parseInt(view.getNrQueuesText());
				model.setNrQueues(nrQueues);
				view.setNrQueues(nrQueues);
				view.addQueues(nrQueues);
			}
			catch (NumberFormatException ex) {
				model.setNrQueues(Shop.NR_QUEUES);
				view.setNrQueues(Shop.NR_QUEUES);
				view.addQueues(Shop.NR_QUEUES);
				System.err.println("Number Format Exception case 5.");
			}
			
			refreshView.start();
			shopThread.start();
			for(int i = 0; i < queueThreads.size(); i++) {
				queueThreads.get(i).start();
			}
		}
	}
	
	class PauseListener implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			shopThread.suspend();
			for(int i = 0; i < queueThreads.size(); i++) {
				queueThreads.get(i).suspend();
			}
		}
	}
	
	class ResumeListener implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			shopThread.resume();
			for(int i = 0; i < queueThreads.size(); i++) {
				queueThreads.get(i).resume();
			}
		}
	}
	
	class TaskPerformer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int[][] arx = model.clientsStatus();
			String log = model.getLog();
			int[] nrClients = model.getNrClients();
			view.updateQueue(arx);
			view.updateLog(log);
			view.updateNrClients(nrClients);
			view.updateAvgServeTime(model.getAvgServeTime());
			view.updateAvgWaitTime(model.getAvgWaitTime());
			view.updateTotNrClients(model.getTotNrClients());
			if(model.getNrClientsInstant() > model.getMaxNrClients()) {
				model.setMaxNrClients(model.getNrClientsInstant());
				view.updatePeakHour(model.getHour() / 1000);
			}
		}
	}
}