package day3;

import java.util.Scanner;
import java.util.Stack;

public class EBook_postfix {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		String s = sc.nextLine();
		String s = "6528-*2/+"; // 후위표기법으로 표현된 수식

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int a, b;
			switch (c) {
			case '+':
				stack.add(stack.pop() + stack.pop());
				break;
			case '-':
				b = stack.pop();
				a = stack.pop();
				stack.add(a - b);
				break;
			case '*':
				stack.add(stack.pop() * stack.pop());
				break;
			case '/':
				b = stack.pop();
				a = stack.pop();
				stack.add(a / b);
				break;
			default:
				stack.add(Integer.parseInt(c + ""));
			}
			System.out.println(stack);
		}
		System.out.println(stack.pop());
	}

}
