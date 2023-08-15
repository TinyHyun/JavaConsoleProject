package banking6;

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
		
		balance = (int)(balance + (balance * inter) + money); 
		
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
		System.out.println("--------------------------");
	}
	
	
	@Override
	public String toString() {
		return String.format("[보통계좌] %s, 이자율: %d%s\n", super.toString(), (int)(inter * 100), "%%");
	}
}
