package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Exceptions.*;
import Models.Calculator;
import Views.MainView;

public class MainController {
	
	private Calculator calc;
	private MainView view;
	
	public MainController(Calculator calc, MainView view) {
		this.calc = calc;
		this.view = view;
		view.addAdditionListener(new OperationListener(1));
		view.addSubtractionListener(new OperationListener(2));
		view.addMultiplicationListener(new OperationListener(3));
		view.addDivisionListener(new OperationListener(4));
		view.addDifferentiationListener(new OperationListener(5));
		view.addIntegrationListener(new OperationListener(6));
	}
	
	class OperationListener implements ActionListener {
		
		//special field that designates the type of operation a specific button will implement
		private int opCode;
		
		public OperationListener(int opCode) {
			super();
			this.opCode = opCode;
		}
		
		public void actionPerformed(ActionEvent e) {
			//a variable that, in the case of an exception can be used to determine where it happened
			//based on the value that is has at that moment
			int exceptionLocator = 1;
			try {
				view.getWarn1().setVisible(false); // do not display warning message
				view.getWarn2().setVisible(false); //do not display warning message
				calc.setPolynomial(1, view.getP1String()); //obtain first polynomial
				view.getP1L().setText(calc.getPol1());	//add it to the corresponding label
				view.getP1L().setVisible(true);			//activate that label
				if(opCode < 5) {
					exceptionLocator = 2; //change value of the exceptionLocator
					calc.setPolynomial(2, view.getP2String()); //obtain second polynomial
					view.getP2L().setText(calc.getPol2()); //add it to the corresponding label
					view.getP2L().setVisible(true); // activate that label
				}
				view.setResult(calc.getResult(opCode)); //display the result of the operation denoted by opCode
			}
			catch (NoUserInputException ex) {
				view.getP1L().setVisible(false);
				if(opCode < 5) {
					view.getP2L().setVisible(false); 
				}
				if(exceptionLocator == 1) {
					view.getWarn1().setText("No input!"); //display error message
					view.getWarn1().setVisible(true);
				}
				else {
					view.getWarn2().setText("No input!"); //display error message
					view.getWarn2().setVisible(true);
				}
				System.err.println("No Data!");
			}
			catch (WrongDataFormatException ex) {
				view.getP1L().setVisible(false);
				if(opCode < 5) {
					view.getP2L().setVisible(false);
				}
				if(exceptionLocator == 1) {
					view.getWarn1().setText("Wrong input format!"); //display error message
					view.getWarn1().setVisible(true);
				}
				else {
					view.getWarn2().setText("Wrong input format!"); //display error message
					view.getWarn2().setVisible(true);
				}
				System.err.println("Wrong Data!");
			}
			catch (NumberFormatException ex) {
				view.getP1L().setVisible(false);
				if(opCode < 5) {
					view.getP2L().setVisible(false);
				}
				if(exceptionLocator == 1) {
					view.getWarn1().setText("Not number characters!"); //display error message
					view.getWarn1().setVisible(true);
				}
				else {
					view.getWarn2().setText("Not number characters!"); //display error message
					view.getWarn2().setVisible(true);
				}
				System.err.println("Not Number!");
			}
		}
	}
}