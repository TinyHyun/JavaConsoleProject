package banking7.jdbc;

import java.sql.SQLException;

public class SelectSQL extends IConnectImpl {

	private String accountNumber;
//	private String name;
	private int balance;
	
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public int getBalance() {
		return balance;
	}


	public SelectSQL(String accountNumber) {
		super("education", "1234");
		this.accountNumber = accountNumber;
	}



	@Override
	public void execute() {
		
		try {
			/*
			prepared 인터페이스를 통해 인파라미터를 설정하면 문자인 경우 자동으로 ''을 추가하게 되므로
			||연산자를 추가해서 쿼리문을 작성해야 한다.
			*/
			String sql = "SELECT * FROM banking_tb "
					//+ " WHERE name LIKE '%?%' "; //에러발생: 부적합한 열 인덱스
					+ " WHERE accountNumber=?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, accountNumber);
			rs = psmt.executeQuery();
			while(rs.next()) {
//					accountNumber = rs.getString(1);
				balance = rs.getInt(3);

//					System.out.printf("계좌번호: %s, 이름: %s, 잔고: %d\n", accountNumber, name, balance);
				System.out.printf(" 계좌번호: %s, 잔고: %d\n", accountNumber, balance);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}


}
