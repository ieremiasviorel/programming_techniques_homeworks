package Models;

public class RealMonomial extends Monomial {
	
	public RealMonomial(double coeff, int exp) {
		super(coeff, exp);
	}
	
	//adds two monomial and return the result
	//if the monomials are not of the same degree, the current monomial is returned as result
	//if the monomials cancel each other out, null is returned
	public Monomial addMonomial(Monomial m) {
		if(this.equals(m)) {
			Monomial res;
			res = new RealMonomial(this.coeff.doubleValue() + m.coeff.doubleValue(), this.exp);
			if(res.coeff.doubleValue() == 0) {
				return null;
			}
			else {
				return res;
			}
		}
		else {
			return this;
		}
	}
	
	//returns the opposite of the current monomial
	public Monomial oppositeMonomial() {
		return new RealMonomial(-this.coeff.doubleValue(), this.exp);
	}
	
	
	//returns the product obtained from the current monomial with the one received as parameter
	public Monomial multiplyMonomial(Monomial m) {
		return new RealMonomial(this.coeff.doubleValue() * m.coeff.doubleValue(), this.exp + m.exp);
	}
	
	//returns the quotient of the division between the current monomial and the paramenter m
	//if the monomial has a lower degree than m, the quotient is 0, and thus represented as null
	public Monomial divideMonomial(Monomial m) {
		if(this.exp < m.exp) {
			return null;
		}
		else {
			return new RealMonomial(this.coeff.doubleValue() / m.coeff.doubleValue(), this.exp - m.exp).monomialCorrection();
		}
	}
	
	//return the derivated version of the monomial
	public Monomial differentiateMonomial() {
		if(exp == 0) {
			return null;
		}
		else {
			return new RealMonomial(this.exp * this.coeff.intValue(), this.exp - 1);
		}
	}
	
	//returns integrated version of the monomial
	public Monomial integrateMonomial() {
		return new RealMonomial(this.coeff.doubleValue() / (this.exp + 1), this.exp + 1).monomialCorrection();
	}
	
	
	//overridden version of the toString method
	public String toString() {
		double coeffTrimmed = (int)(this.coeff.doubleValue() * 100) / (double)100;
		if(exp > 0) {
			if(coeffTrimmed < 0) {
				return (coeffTrimmed+ "*X^" + exp);
			}
			else {
				return ("+" + coeffTrimmed + "*X^" + exp);
			}
		}
		else {
			if(coeffTrimmed < 0) {
				return (coeffTrimmed +"");
			}
			else {
				return ("+" + coeffTrimmed);
			}
		}
	}
}