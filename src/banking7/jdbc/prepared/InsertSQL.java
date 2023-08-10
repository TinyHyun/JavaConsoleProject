package banking7.jdbc.prepared;

import java.util.Scanner;

import banking7.jdbc.connect.IConnectImpl;

//DB연결을 위한 클래스를 상속한다.
public class InsertSQL extends IConnectImpl {
	
	//생성자: 부모클래스의 생성자를 호출하여 연결한다.
	public InsertSQL() {
		super(ORACLE_DRIVER, "education", "1234");
	}
	
	//쿼리 실행을 위한 멤버메서드
	@Override
	public void execute() {
		
		try {
			//1. 쿼리문 준비: 값의 세팅을 위한 부분을 ?(인파라미터)로 기술한다.
			String query = "INSERT INTO banking_tb VALUES (?, ?, ?)";
			
			//2. prepareStatement 객체생성: 준비한 쿼리문을 인수로 전달한다.
			psmt = con.prepareStatement(query);
			
			//3. 사용자로부터 insert할 내용을 입력받는다.
			Scanner scan = new Scanner(System.in);
			System.out.print("계좌번호: ");
			String accountNumber = scan.nextLine();
			
			System.out.print("이름: ");
			String name = scan.nextLine();
			
			System.out.print("잔고: ");
			int balance = scan.nextInt();
			scan.nextLine();
			
			/*
			4. 인파라미터설정: ?의 순서대로 설정하고 인덱스는 1부터 시작하면 된다.
				자료형이
					숫자면 setInt()
					문자면 setString()
					날짜면 setDate()
				입력값이 문자 혹은 날짜면 ''이 자동으로 추가된다.
			*/
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
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {

		new InsertSQL().execute();
	}

}

