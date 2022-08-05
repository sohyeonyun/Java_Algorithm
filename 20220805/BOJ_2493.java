package day3;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_2493 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 타워 높이, 인덱스
		Stack<int[]> stack = new Stack<>();

		int n = sc.nextInt();
		sc.nextLine();

		int[] results = new int[n];

		String[] inputs = sc.nextLine().split(" ");
		// 왼쪽으로 쏘기 때문에 배열 뒤부터 탐색한다.
		for (int i = n - 1; i >= 0; i--) {
			int tower = Integer.parseInt(inputs[i]);
			// 스택이 비었으면, 그냥 추가
			if (stack.empty()) {
				stack.add(new int[] { tower, i });
			}
			// top <= input
			if (stack.peek()[0] <= tower) {
				// input보다 작은 탑들은 모두 input에 쏘게 된다.
				while (!stack.empty() && stack.peek()[0] <= tower) {
					// top은 input에 레이저를 쏜다
					int[] top = stack.pop(); // {높이, 인덱스}
					results[top[1]] = i + 1;
				}
			}
			// input을 스택에 추가
			stack.add(new int[] { tower, i });

		}

		// 결과 출력
		for (int res : results) {
			System.out.print(res + " ");
		}
	}

}
