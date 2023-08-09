package banking5;

public interface ICustomDefine {

	/*
	계좌개설 => MAKE = 1
	입금 => DEPOSIT = 2
	출금 => WITHDRAW = 3
	전체계좌정보출력 => INQUIRE = 4
	계좌정보삭제 => REMOVE = 5
	프로그램종료 => EXIT = 6
	*/
	int MAKE = 1, DEPOSIT = 2, WITHDRAW = 3, INQUIRE = 4, REMOVE = 5 ,EXIT = 6;
}
