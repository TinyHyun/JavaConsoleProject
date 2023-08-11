package banking7.jdbc;

import java.util.Scanner;

public class BankingSystemMain {

	public static Scanner scan = new Scanner(System.in);
	
	static Account[] accArr = new Account[50];
	static int accCount = 0;

	//메뉴출력
	public static void showMenu() {
		
		System.out.println();
		System.out.println(" ----------------------------------Menu---------------------------------- ");
		System.out.println();
		System.out.print("||  1.계좌개설  ||  ");
		System.out.print("2.입 금  ||  ");
		System.out.print("3.출 금  ||  ");
		System.out.print("4.계좌정보출력  ||  ");
		System.out.println("5.프로그램 종료  ||");
		System.out.println();
		System.out.println(" ------------------------------------------------------------------------ ");
		System.out.println();
		System.out.print(" 선택: ");
		
	}
	
	
	//계좌개설을 위한 함수
	public static void makeAccount() {

		System.out.print(" 계좌번호: "); String accountNumber = scan.nextLine();
		System.out.print(" 고객 이름: "); String name = scan.nextLine();
		System.out.print(" 잔고: "); int balance = scan.nextInt();
		
		InsertSQL insert = new InsertSQL(accountNumber, name, balance);
		insert.execute();
//		System.out.println();
//		System.out.println("계좌계설이 완료되었습니다.");
		
	}
	
	
	//입금
	public static void depositMoney() {
		
		
		System.out.println(" 계좌번호와 입금할 금액을 입력하세요.");
		System.out.println();
		System.out.print(" 계좌번호: "); String accountNumber = scan.nextLine();
		
		SelectSQL select = new SelectSQL(accountNumber);
		select.execute();
		
		int balance = select.getBalance(); 
		
		System.out.print(" 입금액: "); int money = scan.nextInt();
		UpdateSQL update = new UpdateSQL(accountNumber, balance, money);
		update.execute();
		
		
		System.out.printf(" %d원이 입금되었습니다.\n", money);
		
		
//		System.out.println(" 입금이 완료되었습니다.");
	}
	
	
	//출금
	public static void withdrawMoney() {
		
		System.out.println(" 계좌번호와 출금할 금액을 입력하세요.");
		System.out.println();
		System.out.print(" 계좌번호: "); String accountNumber = scan.nextLine();
		
		SelectSQL select = new SelectSQL(accountNumber);
		select.execute();
		
		int balance = select.getBalance(); 
		
		System.out.print(" 출금액: "); int money = scan.nextInt();
		UpdateSQL update = new UpdateSQL(accountNumber, balance, -money);
		update.execute();
		
		
		System.out.printf(" %d원이 출금되었습니다.\n", money);
		
		
		
		
		
	}

	//전체계좌조회
	public static void showAccInfo() {
		System.out.println(" ****************************** 계좌 정보 출력 ****************************** ");
		System.out.println();
		System.out.println(" ------------------------------------------------------------------------ ");
		
		SelectQuery selQuery = new SelectQuery();
		selQuery.execute();
		
		System.out.println(" ------------------------------------------------------------------------ ");
		System.out.println();
		System.out.println(" ------------------------------------------------------------------------ ");
		System.out.println(" 전체계좌정보가 출력되었습니다.");
		System.out.println(" ------------------------------------------------------------------------ ");
		System.out.println();
	}
	
	
	public static void main(String[] args) {

		while (true) {
			//1.메뉴 출력
			showMenu();
			
			//2. 사용자가 선택 할 메뉴
			int choice = scan.nextInt();
			scan.nextLine(); //버퍼날림
			
			
			//3. 선택한 번호에 따른 메서드 호출
			switch (choice) {
			case 1:
				System.out.println();
				System.out.println(" ****************************** 신규 계좌 개설 ****************************** ");
				System.out.println();
				makeAccount();
				break;
			case 2:
				System.out.println();
				System.out.println(" ********************************* 입 금 ********************************* ");
				System.out.println();
				depositMoney();
				break;
			case 3:
				System.out.println();
				System.out.println(" ********************************* 출 금 ********************************* ");
				System.out.println();
				withdrawMoney();
				break;
			case 4:
				System.out.println();
				showAccInfo();
				
				break;
			case 5:
				System.out.println();
				System.out.println(" 프로그램을 종료합니다.");
				return;
			}
		}
	}



}
