package banking6;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public abstract class Account implements Serializable {

	//멤버변수
	protected String accountNumber; //계좌번호
	protected String name; //이름
	protected int balance; //잔액

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
	
	
	
	
	
	
	@Override
	public int hashCode() {
		return accountNumber.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Account acc = (Account)obj;
		if(this.getAccountNumber().equals(acc.getAccountNumber())) {
			return true;
		}
		else {
			return false;
		}
	}

	//전체계좌정보출력
	abstract public void showAccountInfo();
	
	//입급처리
	abstract public boolean plusAccMoney(int money);
	
	
	//출금처리
	public boolean minusAccMoney(int money) {
		//잔고가 마이너스 처리되더라도 조건없이 출금처리함
		balance -= money;
		return true;
	}
	
//	abstract public String printInfo(); 
	
	public String toString() {
		return String.format("계좌번호: %s, 이름: %s, 잔고: %d", accountNumber, name, balance);
	}
	
}































