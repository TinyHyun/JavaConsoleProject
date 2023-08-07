package banking2;

public class HighCreditAccount extends Account {

	private double inter;
	private String level;

	public HighCreditAccount(String accountNumber, String name, int balance, 
			double inter, String level) {
		
		super(accountNumber, name, balance);
		
		this.inter = inter / 100.0;
		this.level = level;
	}
	
	@Override
	public boolean plusAccMoney(int money) {
		
		
		
		return true;
	}
}
