package banking2;

import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		AccountManager accMan = new AccountManager();
		
		while (true) {
			//1.메뉴 출력
			accMan.showMenu();
			
			//2. 사용자가 선택 할 메뉴
			int choice = scan.nextInt();
			scan.nextLine(); //버퍼날림
			
			
			//3. 선택한 번호에 따른 메서드 호출
			switch (choice) {
			case 1:
				System.out.println("******* 신규 계좌 개설 *******");
				accMan.makeAccount();
				break;
			case 2:
				System.out.println("******* 입 금 *******");
				accMan.depositMoney();
				break;
			case 3:
				System.out.println("******* 출 금 *******");
				accMan.withdrawMoney();
				break;
			case 4:
				accMan.showAccInfo();
				break;
			case 5:
				System.out.println("프로그램 종료");
				return;
			}
		}
	}



}
