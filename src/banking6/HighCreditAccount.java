package banking6;

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
			balance = (int)(balance + (balance * inter) + 
					(balance * 0.07) + money);
			break;
			
		case "B":
			balance = (int)(balance + (balance * inter) + 
					(balance * 0.04) + money);
			break;
			
		case "C":
			balance = (int)(balance + (balance * inter) + 
					(balance * 0.02) + money);
			break;
		}

		return true;
	}
	
	@Override
	public void showAccountInfo() {
		
		System.out.println();
		System.out.println("******* 계좌 정보 출력 *******");
		System.out.println("--------------------------");
		System.out.println("계좌번호 > " + accountNumber);
		System.out.println("고객 이름 > " + name);
		System.out.println("잔고 > " + balance);
		System.out.println("기본이자 > " + (int)(inter * 100) + "%");
		System.out.println("신용등급 > " + level);
		System.out.println("--------------------------");
	}
	
//	@Override
//	public String printInfo() {
//
//		String str = 
//				String.format("[신용계좌 %s등급], 계좌번호: %s, 이름: %s, 잔고: %d, 이자율: %d%s\n\n" , level, accountNumber, name, balance, (int)(inter * 100), "%%");
//					
//		return str;
//	}
	
	@Override
	public String toString() {
		return String.format("[신용계좌 %s등급] %s, 이자율: %d%s\n", level, super.toString(), (int)(inter * 100), "%%");
	}
	
}
