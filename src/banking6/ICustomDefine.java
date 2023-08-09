package banking6;

public interface ICustomDefine {

	/*
	계좌개설 => MAKE = 1
	입금 => DEPOSIT = 2
	출금 => WITHDRAW = 3
	전체계좌정보출력 => INQUIRE = 4
	계좌정보삭제 => REMOVE = 5
	자동저장 => AutoSave = 6
	프로그램종료 => EXIT = 7
	*/
	int MAKE = 1, 
		DEPOSIT = 2, 
		WITHDRAW = 3, 
		INQUIRE = 4, 
		REMOVE = 5,
		AutoSave = 6,
		EXIT = 7;
}
