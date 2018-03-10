package Models;

public class IntMonomial extends Monomial {
	
	public IntMonomial(int coeff, int exp) {
		super(coeff, exp);
	}
	
	//the type of the result for the operations below is determind by the type
	//of the parameter the method receives (if it's the case)
	//this type, that will be type of the result is determined using the operator instanceof
	
	
	//adds two monomial and return the result
	//if the monomials are not of the same degree, the current monomial is returned as result
	//if the monomials cancel each other out, null is returned
	public Monomial addMonomial(Monomial m) {
		if(this.equals(m)) {
			Monomial res;
			if(m instanceof RealMonomial) {
				res = new RealMonomial(this.coeff.doubleValue() + m.coeff.doubleValue(), this.exp);
			}
			else {
				res = new IntMonomial(this.coeff.intValue() + m.coeff.intValue(), this.exp);
			}
			if(res.coeff.doubleValue() == 0) {
				return null;
			}
			else
				return res;
		}
		else {
			return this;
		}
	}
	
	//returns the opposite of the current monomial
	public Monomial oppositeMonomial() {
		return new IntMonomial(-this.coeff.intValue(), this.exp);
	}
	
	//returns the product obtained from the current monomial with the one received as parameter
	public Monomial multiplyMonomial(Monomial m) {
		Monomial res;
		if(m instanceof RealMonomial) {
			res = new RealMonomial(this.coeff.doubleValue() * m.coeff.doubleValue(), this.exp + m.exp);
		}
		else {
			res = new IntMonomial(this.coeff.intValue() * m.coeff.intValue(), this.exp + m.exp);
		}
		return res;
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
			return new IntMonomial(this.exp * this.coeff.intValue(), this.exp - 1);
		}
	}
	
	//returns integrated version of the monomial
	public Monomial integrateMonomial() {
		return new RealMonomial(this.coeff.doubleValue() / (this.exp + 1), this.exp + 1).monomialCorrection();
	}
	
	//overridden version of the toString method
	public String toString() {
		if(exp > 0) {
			if(coeff.intValue() < 0) {
				return ("-" + Math.abs(coeff.intValue()) + "*X^" + exp);
			}
			else {
				return ("+" + coeff.intValue() + "*X^" + exp);
			}
		}
		else {
			if(coeff.intValue() < 0) {
				return ("-" + Math.abs(coeff.intValue()));
			}
			else {
				return ("+" + coeff.intValue());
			}
		}
	}
}