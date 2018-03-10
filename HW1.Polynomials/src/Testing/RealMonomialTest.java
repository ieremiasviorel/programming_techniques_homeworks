package Testing;

import org.junit.Test;

import Models.*;

public class RealMonomialTest {

	@Test
	public void testAddition() {
		Monomial mon1 = new RealMonomial(4.2, 3);
		Monomial mon2 = new IntMonomial(-2, 3);
		Monomial mon3 = new RealMonomial(-4.2, 3);
		Monomial mon4 = new IntMonomial(1, 2);
		assert(mon1.addMonomial(mon2).equals(new RealMonomial(2.2, 3)));
		assert(mon1.addMonomial(mon3) == null);
		assert(mon1.addMonomial(mon4).equals(mon1));
	}
	
	@Test
	public void testOpposite() {
		Monomial mon1 = new RealMonomial(4.2, 3);
		assert(mon1.oppositeMonomial().equals(new RealMonomial(-4.2, 3)));
	}
	
	@Test
	public void testMultiplication() {
		Monomial mon1 = new RealMonomial(4.2, 3);
		Monomial mon2 = new RealMonomial(1, 4);
		Monomial mon3 = new RealMonomial(0.25, 1);
		assert(mon1.multiplyMonomial(mon2).equals(new RealMonomial(4.2, 7)));
		assert(mon1.multiplyMonomial(mon3).equals(new RealMonomial(1.05, 4)));
	}
	
	@Test
	public void testDivision() {
		Monomial mon1 = new RealMonomial(4, 3);
		Monomial mon2 = new RealMonomial(8, 2);
		assert(mon1.divideMonomial(mon2).equals(new RealMonomial(0.5, 1)));
		assert(mon2.divideMonomial(mon1) == null);
	}
	
	@Test
	public void testDifferentiation() {
		Monomial mon1 = new RealMonomial(4.2, 3);
		Monomial mon2 = new RealMonomial(4.2, 5);
		assert(mon1.differentiateMonomial().equals(new RealMonomial(12.6, 2)));
		assert(mon2.differentiateMonomial().equals(new IntMonomial(21, 4)));
	}
}