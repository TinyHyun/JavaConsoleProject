package banking2;

public class NormalAccount extends Account {
	
	//멤버변수
	private double inter;

	//생성자
	public NormalAccount(String accountNumber, String name, int balance, double inter) {
		
		//부모 생성자 먼저 호출
		super(accountNumber, name, balance);
		//멤버변수 초기화
		this.inter = inter / 100.0;
	}
	
	@Override
	public boolean plusAccMoney(int money) {
		
		super.balance = (int)(super.balance + (super.balance * inter) + money); 
		
		return true;
	}
}
