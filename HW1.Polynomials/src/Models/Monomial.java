package Models;


public abstract class Monomial implements Comparable<Monomial> {

	protected Number coeff;
	protected int exp;

	//parameterized constructor
	public Monomial(Number coeff, int exp) {
		this.coeff = coeff;
		this.exp = exp;
	}

	//overridden equals method
	//equality is established based on the degree
	public boolean equals(Monomial m) {
		return this.exp == m.exp;
	}
	
	//overridden compareTo method
	//comparison is made based on the degree
	public int compareTo(Monomial m) {
		if(this.exp > m.exp) {
			return -1;
		}
		else if(this.exp == m.exp) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	//abstract methods for the basic operations
	//are to the implemented in the concrete classes
	public abstract Monomial addMonomial(Monomial m);
	public abstract Monomial oppositeMonomial();
	public abstract Monomial multiplyMonomial(Monomial m);
	public abstract Monomial divideMonomial(Monomial m);
	public abstract Monomial differentiateMonomial();
	public abstract Monomial integrateMonomial();
	
	//method that is responsible to correct for the situation when a result is
	//computed as dobule, but is in fact an integer
	public Monomial monomialCorrection() {
		double epsilon = 0.00001;
		if(Math.abs(this.coeff.doubleValue() - Math.floor(this.coeff.doubleValue())) < epsilon) {
			return new IntMonomial((int)Math.floor(this.coeff.doubleValue()), this.exp);
		}
		else {
			if(Math.abs(this.coeff.doubleValue() - Math.ceil(this.coeff.doubleValue())) < epsilon) {
				return new IntMonomial((int)Math.ceil(this.coeff.doubleValue()), this.exp);
			}
		}
		return this;
	}
}