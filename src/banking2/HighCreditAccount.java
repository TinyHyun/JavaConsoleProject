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
		
		switch (level) {
		case "A":
			super.balance = (int)(super.balance + (super.balance * inter) + 
					(super.balance * 0.07) + money);
			break;
			
		case "B":
			super.balance = (int)(super.balance + (super.balance * inter) + 
					(super.balance * 0.04) + money);
			break;
			
		case "C":
			super.balance = (int)(super.balance + (super.balance * inter) + 
					(super.balance * 0.02) + money);
			break;
		}

		return true;
	}
}
