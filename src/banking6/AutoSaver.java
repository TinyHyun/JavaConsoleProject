package banking6;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AutoSaver extends Thread {
	private Set<Account> setAcc;
	
	public AutoSaver(AccountManager accMan) {
		setAcc = accMan.getAccSet();
	}

	@Override
	public void run() {

		while (true) {
			try {
				sleep(5000); // => 5초마다 자동저장
				System.out.println();
				System.out.println("자동저장 했습니다.");
				
				PrintWriter out = 
						new PrintWriter(new FileWriter("src/banking6/AutoSaveAccount.txt"));
				
				for (Account acc : setAcc) {
					
					out.printf(acc.printInfo());
					
				}
				
				out.close();
				System.out.println("txt파일로 저장되었습니다..");
			}
			catch (InterruptedException e) {
				
				System.out.println();
				break;
			}
			catch (IOException e) {
				System.out.println("예외가 발생됬습니다.");
				break;
			}
		}
	}
	
}
