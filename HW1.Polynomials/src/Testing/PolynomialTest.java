package Testing;

import org.junit.Test;

import Models.*;

public class PolynomialTest {

	@Test
	public void testAddTerm() {
		Polynomial p1 = new Polynomial();
		p1.addTerm(new IntMonomial(1, 3));
		p1.addTerm(new RealMonomial(3.14, 2));
		p1.addTerm(new RealMonomial(3.2, 3));
		p1.addTerm(new IntMonomial(4, 0));
		assert(p1.equals(new Polynomial(new Number[]{4.2, 3.14, 0, 4}, 4)));
	}
	
	private Polynomial p1 = new Polynomial(new Number[]{1, 3, -6, 2}, 4);
	private Polynomial p2 = new Polynomial(new Number[]{1, 1, -4, 1, -2}, 5);
	private Polynomial p3 = new Polynomial(new Number[]{1, 0, 0}, 3);
	private Polynomial p4 = new Polynomial(new Number[]{0}, 1);
	private Polynomial p5 = new Polynomial(new Number[]{1, -1}, 2);
	
	@Test
	public void testAddition() {
		Polynomial res = new Polynomial(new Number[]{1, 2, -1, -5, 0}, 5);
		assert(res.equals(p1.addPolynomial(p2)));
	}
	
	@Test
	public void testSubtraction() {
		Polynomial res = new Polynomial(new Number[]{-1, 0, 7, -7, 4}, 5);
		assert(res.equals(p1.subtractPolynomial(p2)));
	}
	
	@Test
	public void testMultiplication() {
		assert(p1.multiplyPolynomial(p3).equals(new Polynomial(new Number[]{1, 3, -6, 2, 0, 0}, 6)));
		assert(p1.multiplyPolynomial(p4).equals(new Polynomial()));
		assert(p1.multiplyPolynomial(p5).equals(new Polynomial(new Number[]{1, 2, -9, 8, -2}, 5)));
	}
	
	@Test
	public void tesdDivision() {
		Polynomial res[] = new Polynomial[2];
		res[0] = new Polynomial(new Number[]{1, 4, -2}, 3);
		res[1] = new Polynomial();
		assert(res[0].equals(p1.dividePolynomial(p5)[0]));
		assert(res[1].equals(p1.dividePolynomial(p5)[1]));
	}
	
	@Test
	public void testDifferentiation() {
		assert(new Polynomial(new Number[]{3, 6, -6}, 3).equals(p1.differentiatePolynomial()));
	}
}