package day3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class EBook_browser {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		String[] s;
		char action;
		String inputUrl;

		Stack<String> backwardStack = new Stack<>();
		Stack<String> forwardStack = new Stack<>();
		String curUrl = "http://www.ssafy.com"; // 기본 홈페이지

		while (true) {
			s = sc.nextLine().split(" ");
			action = s[0].charAt(0);

			switch (action) {
			case 'V': // url 방문
				if (!forwardStack.isEmpty()) {
					forwardStack.clear();
				}
				backwardStack.add(curUrl); // 현재 url은 스택으로
				curUrl = s[1]; // 새로운 url은 현재 url로
				break;
			case 'B': // 뒤로가기
				if (!backwardStack.isEmpty()) {
					// cur -> forward
					forwardStack.add(curUrl);
					// back -> cur
					curUrl = backwardStack.pop();
				}
				break;
			case 'F': // 앞으로
				if (!forwardStack.isEmpty()) {
					// cur -> back
					backwardStack.add(curUrl);
					// for -> cur
					curUrl = forwardStack.pop();
				}
				break;
			case 'Q':
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}

			System.out.println("입력된 행동: \t" + action);
			System.out.println("forwardStack: \t" + forwardStack);
			System.out.println("현재 url: \t" + curUrl);
			System.out.println("backwardStark: \t" + backwardStack);
			System.out.println();

		}
	}

}
