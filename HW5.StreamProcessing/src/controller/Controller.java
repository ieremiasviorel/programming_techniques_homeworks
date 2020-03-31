package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import model.Processor;
import view.View;

public class Controller {

	private Processor processor;
	@SuppressWarnings("unused")
	private View view;
	
	public Controller(Processor processor, View view) {
		this.processor = processor;
		this.view = view;
		for(int i = 0; i < 5; i++) {
			view.addButtonListener(new OperationListener(i), i);
		}
	}
	
	class OperationListener implements ActionListener {
		
		private int identifier;
		
		public OperationListener(int identifier) {
			super();
			this.identifier = identifier;
		}
		
		public void actionPerformed(ActionEvent e) {
			BufferedWriter writer = null;
	        try {
	        	String fileName = "Task " + (this.identifier + 1) + ".txt";
	            File outputFile = new File(fileName);

	          	writer = new BufferedWriter(new FileWriter(outputFile));
	          	switch(identifier) {
	    		case 0 : writer.write(processor.countDistinctDays() + "");
	    		break;
	    		case 1 : writer.write(processor.countNoActivities().toString());
	    		break;
	    		case 2 : writer.write(processor.activitiesPerDay().toString());
	    		break;
	    		case 3 : writer.write(processor.totalTimePerActivity().toString());
	    		break;
	    		case 4 : writer.write(processor.listShortActivities().toString());
	    		break;
	    		}
	          	ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileName);
	    		pb.start();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception ex) {
	            	ex.printStackTrace();
	            }
	        }
		}
	}
}