package day3;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 유한 개의 정수로 이루어진 집합이 있을 때, 이 집합의 부분집합 중에서
 * 원소를 모두 더한 값이 0이 되는 경우가 있는지
 * 예) {-7, -3, -2, 5, 8} -> {-3, -2, 5} 는 합이 0이므로 참이다.
 */

public class EBook_subset {

	static int n;
	static int[] inputs;
	static boolean[] isSelected;
	static boolean answer = false;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = Integer.parseInt(sc.nextLine());
		inputs = new int[n];
		isSelected = new boolean[n];

		String[] s = sc.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			inputs[i] = Integer.parseInt(s[i]);
		}

		subset(0);
		System.out.println(answer);
	}

	static void subset(int cnt) {
		if (cnt == n) {

			int sum = 0;
			boolean flag = false; // 원소가 하나라도 있는지 확인
			for (int i = 0; i < n; i++) {
				if (isSelected[i]) {
					sum += inputs[i];
					flag = true;
				}
			}
			if (sum == 0 && flag) {// 합이 0이고 공집합이 아니다.
				answer = true;
				// 그 때의 부분집합 출력
				System.out.print("{ ");
				for (int i = 0; i < n; i++) {
					System.out.print(isSelected[i] ? inputs[i] + ", " : "");
				}
				System.out.print("}" + "\n");
			}

			return;
		}

		isSelected[cnt] = true;
		subset(cnt + 1);
		isSelected[cnt] = false;
		subset(cnt + 1);
	}
}
