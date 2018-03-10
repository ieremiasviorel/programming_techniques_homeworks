package Models;

import java.util.Collections;
import java.util.LinkedList;

public class Polynomial {

	private LinkedList<Monomial> monomials;
	
	//creates a Polynomial based on an existing list of Monomial objects
	public Polynomial(LinkedList<Monomial> monomials) {
		Collections.sort(monomials);
		this.monomials = monomials;
	}
	
	//creates an empty Polynomial
	public Polynomial() {
		this(new LinkedList<Monomial>());
	}
	
	//creates a Polynomial from an array of numbers, representing the coefficients 
	public Polynomial(Number array[], int n) {
		this.monomials = new LinkedList<Monomial>();
		for(int i = 0; i < n; i++) {
			if(array[i].doubleValue() != 0) {
				this.addTerm(new RealMonomial(array[i].doubleValue(), n - i - 1).monomialCorrection());
			}
		}
	}
	
	//adds a new term to the polynomial
	//if a term of similar degree already exists, the 2 are sumed
	//else the monomial is simply added to the polynomial
	public void addTerm(Monomial m) {
		if(m != null) {
			if(getMonomialPosition(m) >= 0) {
				int pos = getMonomialPosition(m);
				Monomial toAdd = m.addMonomial(monomials.get(pos));
				monomials.remove(pos);
				if(toAdd != null) {
					monomials.add(pos, toAdd.monomialCorrection());
					return;
				}
			}
			else {
				monomials.add(m);
			}
		}
	}
	
	//adds the 2 polynomials and outputs the result
	public Polynomial addPolynomial(Polynomial p) {
		Polynomial result = new Polynomial();
		for(Monomial mon : this.monomials) {
			result.addTerm(mon);
		}
		for(Monomial mon : p.monomials) {
			result.addTerm(mon);
		}
		Collections.sort(result.monomials);
		return result;
	}
	
	//subtracts the 2 polynomials and outputs the result
	public Polynomial subtractPolynomial(Polynomial p) {
		Polynomial result = new Polynomial();
		for(Monomial mon : this.monomials) {
			result.addTerm(mon);
		}
		for(Monomial mon : p.monomials) {
			result.addTerm(mon.oppositeMonomial());
		}
		Collections.sort(result.monomials);
		return result;
	}
	
	
	//multiplies the 2 polynomials and outputs the result
	public Polynomial multiplyPolynomial(Polynomial p) {
		Polynomial result = new Polynomial();
		for(Monomial m1 : this.monomials) {
			for(Monomial m2 : p.monomials) {
				result.addTerm(m1.multiplyMonomial(m2));
			}
		}
		Collections.sort(result.monomials);
		return result;
	}
	
	//divides the 2 polynomials and outputs the result as an array of 2 polynomials, where
	//the first one is the quotient and the second one is the remainder
	public Polynomial[] dividePolynomial(Polynomial p) {
		Polynomial result[] = new Polynomial[2];
		result[0] = new Polynomial();
		result[1] = new Polynomial();
		Polynomial dividend = this;
		Polynomial divisor = p;
		while(dividend.getDegree() >= divisor.getDegree()) {
			Polynomial multiplyFactor = new Polynomial();
			multiplyFactor.addTerm(dividend.monomials.getFirst().divideMonomial(divisor.monomials.getFirst()));
			result[0].addTerm(multiplyFactor.monomials.getFirst());
			dividend = dividend.subtractPolynomial(divisor.multiplyPolynomial(multiplyFactor));
		}
		result[1] = this.subtractPolynomial(result[0].multiplyPolynomial(p));
		return result;
	}
	
	//differentiates the polynomials and outputs the result
	public Polynomial differentiatePolynomial() {
		Polynomial result = new Polynomial();
		for(Monomial mon : monomials) {
			result.addTerm(mon.differentiateMonomial());
		}
		return result;
	}

	//integrates the polynomials and outputs the result
	public Polynomial integratePolynomial() {
		Polynomial result = new Polynomial();
		for(Monomial mon : monomials) {
			result.addTerm(mon.integrateMonomial());
		}
		result.addTerm(new IntMonomial((int)(Math.random() * 100), 0));
		return result;
	}
	
	public boolean equals(Polynomial m) {
		if(this.subtractPolynomial(m).monomials.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		Collections.sort(monomials);
		String s = new String();
		if(monomials.size() == 0) {
			return "0";
		}
		for(Monomial mon : monomials) {
			s = s + mon.toString();
		}
		return s;
	}
	
	//returns the position of any monomial with same degree as m inside a polynomial
	//if there is no such monomial, -1 is returned
	private int getMonomialPosition(Monomial m) {
		int pos = 0;
		for(Monomial mon : monomials) {
			if(mon.equals(m)) {
				return pos;
			}
			pos++;
		}
		return -1;
	}
	
	//return the degree of the polynomial
	public int getDegree() {
		Collections.sort(monomials);
		if(monomials.size() > 0) {
			return monomials.getFirst().exp;
		}
		else {
			return -1;
		}
	}
}