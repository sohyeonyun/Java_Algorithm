package day3;

import java.util.Scanner;

/*
 * 백준 15652 - n과 m (4)
 * 중복조합
 */
public class BOJ_15652 {
	static int n, m;
	static int[] nums;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		nums = new int[m];

		repComb(0, 1);

	}

	static void repComb(int cnt, int start) {
		if (cnt == m) {
			for (int num : nums) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}

		for (int i = start; i <= n; i++) {
			nums[cnt] = i;
			repComb(cnt + 1, i);
		}
	}

}
