package banking7.jdbc.prepared;

import java.sql.SQLException;

import banking7.jdbc.connect.IConnectImpl;

public class SelectSQL extends IConnectImpl {

	private String accountNumber;
	private String name;
	private int balance;
	
	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public SelectSQL() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		
		try {
			while(true) {
				/*
				prepared 인터페이스를 통해 인파라미터를 설정하면 문자인 경우 자동으로 ''을 추가하게 되므로
				||연산자를 추가해서 쿼리문을 작성해야 한다.
				*/
				String sql = "SELECT * FROM banking_tb "
						//+ " WHERE name LIKE '%?%' "; //에러발생: 부적합한 열 인덱스
						+ " WHERE accountNumber LIKE '%'||?||'%' ";
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, scanValue("찾는 계좌"));
				rs = psmt.executeQuery();
				while(rs.next()) {
					accountNumber = rs.getString(1);
					name = rs.getString(2);
					balance = rs.getInt(3);

					System.out.printf("계좌번호: %s, 이름: %s, 잔고: %d\n", accountNumber, name, balance);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {

		new SelectSQL().execute();
	}

}
