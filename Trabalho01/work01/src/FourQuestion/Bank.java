package FourQuestion;

public class Bank {
	
	public String Name;
	
	public String BranchName;
	
	public String BranchAccount;
	
	public String BranchAccountDigit;
	
	public String Account;
	
	public double Balance;
	
	public boolean hasHaveAccountBalance() {
		if (this.Balance > 0)
			return true;
		
		return false;
	}
}
