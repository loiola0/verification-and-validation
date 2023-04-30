package FirstQuestion;

public abstract class FahrenheitCelciusConverter {
	
	public static int toFahrenheit(int celsiusTemperature) {
		
		if (celsiusTemperature < -212)
		{
			IllegalArgumentException erro = new IllegalArgumentException();
	      throw erro;
		}
	    
	    double result = 0;
		
		result = (celsiusTemperature * 1.8) + 32;
		
		return (int)result;
	}
	
	public static int toCelcius(int fahrenheitTemperature) {
		
		if (fahrenheitTemperature < -128)
		{
			IllegalArgumentException erro = new IllegalArgumentException();
	      throw erro;
		}
		
		double result = 0;
		
		result = (fahrenheitTemperature - 32) / 1.8;
		
		return (int)result;
	}
}
