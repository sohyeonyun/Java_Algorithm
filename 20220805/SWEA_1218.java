package day3;

import java.util.Scanner;
import java.util.Stack;

/*
 * SWEA 1218 - 괄호 짝짓기
 * 문자열에서 괄호의 짝이 맞는지 확인
 */

public class SWEA_1218 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 1;

		for (int t = 1; t <= 10; t++) {
			result = 1;

			int n = Integer.parseInt(sc.nextLine());
			String s = sc.nextLine();
			
			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < n; i++) {
				char c = s.charAt(i);
				// 여는 괄호면 스택에 삽입
				if (c == '(' || c == '[' || c == '{' || c == '<') {
					stack.push(c);
				} else { // 닫는 괄호면 스택에서 꺼낸 후 비교
					char pop = stack.pop();
					if(pop == '(' && c == ')') continue;
					if(pop == '[' && c == ']') continue;
					if(pop == '{' && c == '}') continue;
					if(pop == '<' && c == '>') continue;
					result = 0;
					break;
				}
			}

			System.out.printf("#%d %d%n", t, result);
		}
	}

}
