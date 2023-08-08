package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;



public class AccountManager {
	
	Scanner scan = new Scanner(System.in);

	private Account[] accArr = new Account[50];
	private int accCount = 0;
	

	//메뉴출력
	public void showMenu() {
		
		System.out.println("--------Menu--------");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입 금");
		System.out.println("3. 출 금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 프로그램 종료");
		System.out.print("선택: ");
	}
	

	//계좌개설을 위한 함수
	public void makeAccount() {
		
		System.out.println("--------계좌선택--------");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		System.out.print("선택: "); int choice = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호: "); String accountNumber = scan.nextLine();
		System.out.print("고객 이름: "); String name = scan.nextLine();
		System.out.print("잔고: "); int balance = scan.nextInt();
		System.out.print("기본이자%(정수형태로입력): "); int inter = scan.nextInt();
		scan.nextLine();
		
		Account acc = null;
		switch (choice) {
		case 1:
			acc = new NormalAccount(accountNumber, name, balance, inter);
			break;
		case 2:
			System.out.print("신용등급(A,B,C등급): "); String level = scan.nextLine();
			level = level.toUpperCase();
			acc = new HighCreditAccount(accountNumber, name, balance, inter, level);
			break;
		}
		accArr[accCount++] = acc;
		
		System.out.println("계좌계설이 완료되었습니다.");

	}
	
	
	//입금
	public void depositMoney() {
		
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: "); String accountNum = scan.nextLine();
		
		int money; 
		
		while (true) {
			try {
				System.out.print("입금액: "); 
				money = scan.nextInt();
				scan.nextLine();
			}
			catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("문자는 입력할수 없습니다.");
				continue;
			}
			
			try {
				
				if (money < 0) {
					System.out.println("음수는 입금할 수 없습니다.");
				}
				else if (!(money%500 == 0)) {
					System.out.println("입금액은 500원 단위로만 가능합니다.");
				}
				else {
					break;
				}
				
				String msgError = "다시 입력하세요.";
				
				MenuSelectException ex = new MenuSelectException(msgError);
				throw ex;
				
			}
			catch (MenuSelectException e) {
				
				System.out.println("[예외발생] " + e.getMessage());
			}
			
			
		}
		
		
		for (int i=0 ; i<accCount ; i++) {
			if(accArr[i].getAccountNumber().equals(accountNum)) {
				accArr[i].plusAccMoney(money);
				System.out.println("입금이 완료되었습니다.");
				break;
			}
		}

	}
	
	
	//출금
	public void withdrawMoney() {
		
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: "); String accountNum = scan.nextLine();
		
		int money;
		
		while (true) {
			try {
				System.out.print("출금액: "); 
				money = scan.nextInt();
				scan.nextLine();
			}
			catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("문자는 입력할수 없습니다.");
				continue;
			}
			
			try {
				if (money < 0) {
					System.out.println("음수는 출금할 수 없습니다.");
				}
				else if (!(money%1000 == 0)) {
					System.out.println("출금은 1000원 단위로만 출금 가능합니다.");
				}
				else {
					break;
				}
				
				String msgError = "다시 입력하세요.";
				
				MenuSelectException ex = new MenuSelectException(msgError);
				throw ex;
			}
			catch (MenuSelectException e) {
				
				System.out.println("[예외발생] " + e.getMessage());
			}
		}
		
		
		for (int i=0 ; i<accCount ; i++) {
			if(accArr[i].getAccountNumber().equals(accountNum)) {
				if(money > accArr[i].getBalance()) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					System.out.print("- YES / NO - ");
					String yn = scan.nextLine();
					
					if(yn.toUpperCase().equals("YES")) {
						System.out.println("금액 전체를 출금처리합니다.");
						
						accArr[i].minusAccMoney(accArr[i].getBalance());
					}
					else {
						System.out.println("출금요청을 취소합니다.");
					}
				}
				else {
					accArr[i].minusAccMoney(money);
					System.out.println("출금이 완료되었습니다.");

				}
				break;
			}
		}
	}

	//전체계좌조회
	public void showAccInfo() {
		for (int i=0 ; i<accCount ; i++) {
			accArr[i].showAccountInfo();
		}
		System.out.println("전체계좌정보가 출력되었습니다.");
		System.out.println("--------------------------");
		System.out.println();
	} 
}
