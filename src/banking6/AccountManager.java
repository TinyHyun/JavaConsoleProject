package banking6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;



public class AccountManager {
	
	Scanner scan = new Scanner(System.in);

//	private Account[] accArr = new Account[50];
	private Set<Account> accSet = new HashSet<Account>();
	AutoSaver saver = new AutoSaver("선풍기");

	public Set<Account> getAccSet() {
		return accSet;
	}


	//메뉴출력
	public void showMenu() {
		
		System.out.println("--------Menu--------");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입 금");
		System.out.println("3. 출 금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 계좌정보삭제");
		System.out.println("6. 저장옵션");
		System.out.println("7. 프로그램 종료");
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
		boolean flag = accSet.add(acc);
		if (flag) {
			
			System.out.println("계좌계설이 완료되었습니다.");
		}
		else {
			
			System.out.println("계좌가 중복됩니다. 덮어쓸까요?");
			System.out.print("--YES / NO--");
			String yn = scan.nextLine();
			yn = yn.toUpperCase();
			
			switch (yn) {
			case "YES":
				accSet.remove(acc);
				accSet.add(acc);
				System.out.println("정보를 덮어썼습니다.");
				break;
			case "NO":
				System.out.println("기존의 정보를 유지합니다.");
				break;
			}
			
		}
		

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
		
		
//		for (int i=0 ; i<accSet.size() ; i++) {
//			if(accArr[i].getAccountNumber().equals(accountNum)) {
//				accArr[i].plusAccMoney(money);
//				System.out.println("입금이 완료되었습니다.");
//				break;
//			}
//		}
//		
		for (Account acc : accSet) {
			if (acc.getAccountNumber().equals(accountNum)) {
				acc.plusAccMoney(money);
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
		
		
//		for (int i=0 ; i<accCount ; i++) {
//			if(accArr[i].getAccountNumber().equals(accountNum)) {
//				if(money > accArr[i].getBalance()) {
//					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
//					System.out.print("- YES / NO - ");
//					String yn = scan.nextLine();
//					
//					if(yn.toUpperCase().equals("YES")) {
//						System.out.println("금액 전체를 출금처리합니다.");
//						
//						accArr[i].minusAccMoney(accArr[i].getBalance());
//					}
//					else {
//						System.out.println("출금요청을 취소합니다.");
//					}
//				}
//				else {
//					accArr[i].minusAccMoney(money);
//					System.out.println("출금이 완료되었습니다.");
//
//				}
//				break;
//			}
//		}
		
		
		for (Account acc : accSet) {
			if (acc.getAccountNumber().equals(accountNum)) {
				if (money > acc.getBalance()) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					System.out.print("- YES / NO - ");
					String yn = scan.nextLine();
					
					if (yn.toUpperCase().equals("YES")) {
						System.out.println("금액 전체를 출금처리합니다.");
						
						acc.minusAccMoney(acc.getBalance());
					}
					else {
						System.out.println("출금요청을 취소합니다.");
					}
				}
				else {
					acc.minusAccMoney(money);
					System.out.println("출금이 완료되었습니다.");

				}
				break;
			}
		}

	}

	//전체계좌조회
	public void showAccInfo() {
		
//		for (int i=0 ; i<accCount ; i++) {
//			accArr[i].showAccountInfo();
//		}
		
		for (Account acc : accSet) {
			acc.showAccountInfo();
		}

		System.out.println("전체계좌정보가 출력되었습니다.");
		System.out.println("--------------------------");
		System.out.println();
	} 
	
	
	public void removeAccount() {
		
		System.out.println("삭제할 계좌번호를 입력하세요.");
		System.out.print("계좌번호: ");
		String removeAccNum = scan.nextLine();
		
		for (Account acc : accSet) {
			
			if (acc.getAccountNumber().equals(removeAccNum)) {
				
				System.out.println("계좌정보를 삭제합니다.");
				accSet.remove(acc);
				System.out.println("--------------------------");
				System.out.println();
				return;
			}
		}
		System.out.println("계좌를 찾지못했습니다.");
	}
	
	
	//저장
	public void objOutputStream() {
		
		try {
			ObjectOutputStream out = 
				new ObjectOutputStream(new FileOutputStream("src/banking6/AccountInfo.obj"));
			
//			for (Account acc : accSet) {
//				out.writeObject(acc);
//			}
			
			out.writeObject(accSet);
			
			out.close();
			
			System.out.println("저장에 성공하였습니다.");
		}

		catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		}
		catch (IOException e) {
			System.out.println("뭔가 없습니다.");
		}
	}
	
	//불러오기
	public void objInputStream() {
		
		System.out.println("저장된 계좌정보를 불러옵니다.");
		
		try {
			ObjectInputStream in = 
				new ObjectInputStream(new FileInputStream("src/banking6/AccountInfo.obj"));
			
			accSet = (Set<Account>)in.readObject();
			
			in.close();
			
			System.out.printf("%d개의 정보를 불러왔습니다.\n", accSet.size());
		}
		catch (ClassCastException e) {
			System.out.println("한서가 알빠아니래요.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("객체를 찾을 수 없습니다.");
		}
		catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		}
		catch (IOException e) {
			System.out.println("뭔가 없습니다.");
		}
	}

	
	//자동저장
	public void autoSave() {
		
		System.out.println("자동저장을 하시겠습니끼?");
		System.out.print("--ON / OFF--");
		String saveChoice = scan.nextLine();
		saveChoice = saveChoice.toUpperCase();
		
		switch (saveChoice) {
		case "ON":
			try {
				System.out.println("자동저장을 실행합니다.");
				
				saver.setName("오토세이버");
				saver.setDaemon(true);
				saver.start();
			}
			catch (IllegalThreadStateException e) {
				System.out.println("이미 자동저장이 실행되고있습니다.");
			}
			break;
			
		case "OFF":
			System.out.println("자동저장을 멈춤니다.");
			saver.interrupt();
			break;
		}

	}
}






























