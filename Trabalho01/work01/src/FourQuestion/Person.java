package FourQuestion;

import java.sql.Date;
import java.util.Calendar;

public class Person {
	
	public String Name;
	
	public Date BirthDate;
	
	public String DocumentNumber;
	
	public boolean cameOfAge() {
		return this.BirthDate.getYear() - Calendar.YEAR >= 18; 
	}
}
