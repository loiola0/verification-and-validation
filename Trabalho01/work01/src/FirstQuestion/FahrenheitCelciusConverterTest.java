package FirstQuestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FahrenheitCelciusConverterTest {
	@Test
	public void shouldConvertCelciusToFahrenheit() {
		assertEquals(32, FahrenheitCelciusConverter.toFahrenheit(0));
		assertEquals(98, FahrenheitCelciusConverter.toFahrenheit(37));
		assertEquals(212, FahrenheitCelciusConverter.toFahrenheit(100));
		assertThrows(IllegalArgumentException.class, () -> FahrenheitCelciusConverter.toFahrenheit(-400));
	}
	@Test
	public void shouldConvertFahrenheitToCelcius() {
		assertEquals(0, FahrenheitCelciusConverter.toCelcius(32));
		assertEquals(37, FahrenheitCelciusConverter.toCelcius(100));
		assertEquals(100, FahrenheitCelciusConverter.toCelcius(212));
		assertThrows(IllegalArgumentException.class, () -> FahrenheitCelciusConverter.toCelcius(-400));
	}
}