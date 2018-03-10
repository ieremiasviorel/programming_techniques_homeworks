package Testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IntMonomialTest.class, PolynomialTest.class, RealMonomialTest.class })
public class AllTests {
}
