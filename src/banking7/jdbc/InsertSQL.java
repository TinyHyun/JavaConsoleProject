package banking7.jdbc;

import java.util.Scanner;

//DB연결을 위한 클래스를 상속한다.
public class InsertSQL extends IConnectImpl {
	
	private String accountNumber;
	private String name;
	private int balance;
	
	
	public InsertSQL(String accountNumber, String name, int balance) {
		super("education", "1234");
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}

	//생성자: 부모클래스의 생성자를 호출하여 연결한다.
	public InsertSQL() {
		super("education", "1234");
	}
	
	//쿼리 실행을 위한 멤버메서드
	@Override
	public void execute() {
		
		try {
			//1. 쿼리문 준비: 값의 세팅을 위한 부분을 ?(인파라미터)로 기술한다.
			String query = "INSERT INTO banking_tb VALUES (?, ?, ?)";
			
			//2. prepareStatement 객체생성: 준비한 쿼리문을 인수로 전달한다.
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, accountNumber);
			psmt.setString(2, name);
			psmt.setInt(3, balance);
			
			//날짜입력1: 날짜를 문자열로 입력하는 경우. 기본서식을 맞춰서 기술한다.
			//psmt.setString(4, "1999-01-28");
			
			//날짜입력2: Date형으로 입력하는 경우
			/*
			현재날짜를 Java에서 입력하는 경우 아래와 같이 변환과정을 거쳐야한다.
			Util패키지로 시간을 얻어온 후 sql패키지로 타입을 변환한다.
			이때는 date형으로 입력되므로 setDate()로 인파라미터를 설정해야한다.
			*/
//			java.util.Date utilDate = new java.util.Date();
//			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//			psmt.setDate(4, sqlDate);
			
			//5. 쿼리실행 및 결과값 반환
			psmt.executeUpdate();
			System.out.println(" 계좌가 개설되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

}

