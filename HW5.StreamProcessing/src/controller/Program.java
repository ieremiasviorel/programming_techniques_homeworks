package controller;
import model.Processor;
import view.View;

public class Program {
	
	public static void main(String[] args) {

		new Controller(new Processor(), new View());
		System.out.println(new Processor().totalTimePerActivity());
	}

}
