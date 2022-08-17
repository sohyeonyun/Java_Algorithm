package day3;

import java.util.Scanner;

/*
 * BOJ 2023 - 신기한 소수
 * 
 */
public class BOJ_2023 {

	static int[] nums;
	static int n;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		nums = new int[n];

		// 자릿수, 현재 숫자
		prime(0, "");
	}

	static void prime(int cnt, String x) {
		// n자리수면 종료
		if (cnt == n) {
			for (int num : nums) {
				System.out.print(num);
			}
			System.out.println();
			return;
		}

		// 현재 수 x에 0부터 9까지 덧붙여서 소수인지 확인
		for (int i = 0; i <= 9; i++) {
			int number = Integer.parseInt(x + i);
			if (!isPrime(number))
				continue;
			nums[cnt] = i;
			prime(cnt + 1, String.valueOf(number));
		}
	}

	static boolean isPrime(int number) {
		// 0, 1은 소수가 아님.
		if (number == 0 || number == 1)
			return false;
		// 2 ~ root(number) 까지 나눠진다면 소수가 아님.
		for (int i = 2; i <= (int) Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

}
