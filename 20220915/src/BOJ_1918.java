import java.util.Scanner;
import java.util.Stack;

/*
 * 후위 표기식  
 */
public class BOJ_1918 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		String s = sc.nextLine();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.pop(); // 남아 있는 ( 괄호
			} else if (c == '*' || c == '/') {
				while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
					sb.append(stack.pop());
				}
				stack.push(c);
			} else if (c == '+' || c == '-') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.push(c);
			} else {
				sb.append(c);
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb);

	}

}