package banking7.jdbc;

import java.util.Scanner;

public class Account {

	//멤버변수
	private String accountNumber; //계좌번호
	private String name; //이름
	private int balance; //잔액

	
	//메소드 호출을 위한 기본 생성자
	public Account() {}

	//생성자
	public Account(String accountNumber, String name, int balance) {
		
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}

	//get
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	//set
	public void setBalance(int balance) {
		this.balance += balance;
	}
	
	
	//전체계좌정보출력
	public void showAccountInfo() {
		
		System.out.println();
		System.out.print("계좌번호: " + accountNumber);
		System.out.print("  고객 이름: " + name);
		System.out.println("  잔고: " + balance);
	}
	
	//입급처리
	public boolean plusAccMoney(int money) {
		//1단계에서 별도의 검사를 하지않고 입금처리함
		balance += money;
		return true;
	}
	
	
	//출금처리
	public boolean minusAccMoney(int money) {
		//잔고가 마이너스 처리되더라도 조건없이 출금처리함
		balance -= money;
		return true;
	}
}































