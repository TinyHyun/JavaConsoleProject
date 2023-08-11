package banking7.jdbc;

import java.sql.SQLException;

public class UpdateSQL extends IConnectImpl {

	private String accountNumber;
	private int balance;
	private int money;
	
	public int getBalance() {
		return balance;
	}


	public void setMoney(int money) {
		this.money = money;
	}


	public UpdateSQL(String accountNumber, int balance, int money) {
		super("education", "1234");
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.money = money;
	}

	@Override
	public void execute() 
	{
		//update 쿼리문 생성
		String sql = "UPDATE banking_tb "
				+ " SET balance=? "
				+ " WHERE accountNumber=?";
		try {
			//prepared객체 생성
			psmt = con.prepareStatement(sql);
			//사용자가 exit을 입력할때까지 계속 실행되는 구조로 구현함.
			
			/*
			인파라미터값 설정시 인덱스만 일치하면 순서는 상관없다.
			scanValue() 메서드가 반환하는 값으로 즉시 인파라미터를
			세팅한다.  
			*/
			psmt.setString(2, accountNumber);
			psmt.setInt(1, balance + money);
			
			//쿼리문 실행 및 결과확인
			psmt.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

}

