package banking6;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AutoSaver extends Thread {
	public AutoSaver(Object obj) {
		
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
				
//				for (Account acc : accSet) {
//					
//					out.printf("%s, %s, %d\n" , acc.accountNumber, acc.name, acc.balance);
//					
//				}
				
				out.close();
				System.out.println("txt파일로 저장되었습니다..");
			}
			catch (InterruptedException e) {
				
				System.out.println();
				System.out.println("자동저장 실패!!");
				break;
			}
			catch (IOException e) {
				System.out.println("예외가 발생됬습니다.");
				break;
			}
		}
	}
	
}
