package ThirdQuestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void shouldCalculator() {
		Calculator calculator = new Calculator();
		
		assertEquals(43.0, calculator.add(33.0, 10.0));
		
		double sum = calculator.add(0, 10);
		
		assertEquals(true, verifySum(10, sum));
		
		assertThrows(IllegalArgumentException.class,()-> verifySum(20, sum));
	}
	
	@Test
	public boolean verifySum(double expectedValue, double sum) {
		
		if (expectedValue != sum) {
			 IllegalArgumentException erro = new IllegalArgumentException();
		      throw erro;
		}
		
		return true;
	}
	
}
