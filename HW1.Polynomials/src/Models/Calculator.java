package Models;

import Exceptions.*;

public class Calculator {
	
	private Polynomial p1;
	private Polynomial p2;
	private Polynomial res[];
	
	//initializes calculator
	public Calculator() {
		p1 = new Polynomial();
		p2 = new Polynomial();
		res = new Polynomial[2];
	}
	
	//creates the polynomial specified by selector parameter from the input String s
	public void setPolynomial(int selector, String s) throws WrongDataFormatException, NoUserInputException {
		if(s.trim().isEmpty()) {
			throw new NoUserInputException("No user input!");
		}
		if(!checkInput(s)) {
			throw new WrongDataFormatException("Wrong input format! See example!\n");
		}
		
		String coeffs[] = s.split(" ");
		Double coeffNumeric[] = new Double[coeffs.length];
		int j = 0;
		for(int i = 0; i < coeffs.length; i++) {
			if(!coeffs[i].equals("")) {
				coeffNumeric[j] = Double.parseDouble(coeffs[i]);
				j++;
			}
		}
		if(selector == 1) {
			p1 = new Polynomial(coeffNumeric, j);
		}
		else {
			p2 = new Polynomial(coeffNumeric, j);
		}
	}
	
	private boolean checkInput(String s) {
		for(int i = 0; i < s.length(); i++) {
			if((s.charAt(i) < '0' || s.charAt(i) > '9') && s.charAt(i) != ' ' && s.charAt(i) != '-') {
				return false;
			}
		}
		return true;
	}
	
	//returns as a String object the result of the operation deisgnated by opCode
	public String getResult(int opCode) {
		switch(opCode) {
		case 1:
			res[0] = p1.addPolynomial(p2);
			break;
		case 2:
			res[0] = p1.subtractPolynomial(p2);
			break;
		case 3:
			res[0] = p1.multiplyPolynomial(p2);
			break;
		case 4:
			if(p2.getDegree() > -1) {
				res = p1.dividePolynomial(p2);
			}
			else {
				return "Violation! Division by 0!";
			}
			break;
		case 5:
			res[0] = p1.differentiatePolynomial();
			break;
		case 6:
			res[0] = p1.integratePolynomial();
			break;
		}
		if(opCode == 4) {
			return "q: " + res[0].toString() + "  r: " + res[1].toString();
		}
		else {
			return res[0].toString();
		}
	}
	
	public String getResult() {
		if(res[0] == null)
			return "";
		return res[0].toString();
	}
	
	public String getPol1() {
		return p1.toString();
	}
	
	public String getPol2() {
		return p2.toString();
	}
}