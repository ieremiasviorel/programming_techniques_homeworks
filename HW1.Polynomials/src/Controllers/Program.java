package Controllers;
import Models.Calculator;
import Models.Monomial;
import Models.RealMonomial;
import Views.MainView;

public class Program {
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		MainView view = new MainView();
		@SuppressWarnings("unused")
		MainController controller = new MainController(calc, view);
		Monomial m1 = new RealMonomial(3.4, 2);
		Monomial m2 = new RealMonomial(3.4, 2);
		System.out.println(m1.equals(m2));
		System.out.println(m1.equals(m2));
	}
}