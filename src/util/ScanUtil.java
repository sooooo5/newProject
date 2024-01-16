package util;

import java.util.Scanner;
import util.Print;

public class ScanUtil extends Print{
	
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);
	
	public static int menu() {
		return nextInt(customOrange + "메뉴 선택 : " + exit);
	}
	public static String nextLine(String print) {
		System.out.print(customOrange + print + exit);
		return nextLine();
	}
	
	private static String nextLine() {
		return sc.nextLine();
	}
	
	public static int nextInt(String print) {
		System.out.print(print);
		return nextInt();
	}
	
	private static int nextInt() {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("잘못 입력!!");
			}
		}
	}
}
