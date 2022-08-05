package day3;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 15654 - n과 m (5)
 * 입력 받은 수 중에서 순열
 */
public class BOJ_15654 {
	static int n, m;
	static int[] nums;
	static boolean[] isSelected;
	static int[] inputs;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		inputs = new int[n];
		isSelected = new boolean[n];
		nums = new int[m];
		for (int i = 0; i < n; i++) {
			inputs[i] = sc.nextInt();
		}

		Arrays.sort(inputs);
		perm(0);

	}

	static void perm(int cnt) {
		if (cnt == m) {
			for (int num : nums) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			nums[cnt] = inputs[i];
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

}
