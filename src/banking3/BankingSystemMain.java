package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		AccountManager accMan = new AccountManager();
		
		while (true) {
			//1.메뉴 출력
			accMan.showMenu();
			
			int choice;
			
			//2. 사용자가 선택 할 메뉴
			try {
				choice = scan.nextInt();
				scan.nextLine(); //버퍼날림
			}
			catch (InputMismatchException e) {
				System.out.println("[오류발생] 문자는 입력할 수 없습니다.");
				scan.nextLine();
				continue;
			}
			
			
			//3. 선택한 번호에 따른 메서드 호출
			switch (choice) {
			case MAKE:
				System.out.println("******* 신규 계좌 개설 *******");
				accMan.makeAccount();
				break;
			case DEPOSIT:
				System.out.println("******* 입 금 *******");
				accMan.depositMoney();
				break;
			case WITHDRAW:
				System.out.println("******* 출 금 *******");
				accMan.withdrawMoney();
				break;
			case INQUIRE:
				accMan.showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램 종료");
				return;
			default :
				try {
					
					String msgError = "다시 입력하세요";
					MenuSelectException ex = new MenuSelectException(msgError);
					throw ex;
				}
				catch (MenuSelectException e) {

					System.out.println("[오류발생] " + e.getMessage());
				}
				
			}
		}
	}



}
