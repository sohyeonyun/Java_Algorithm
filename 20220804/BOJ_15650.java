import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 15650 - n과 m (2)
 * nCm 의 모든 경우를 나열하라.
 */

public class BOJ_15650 {

	static int n, m;
	static int[] nums;
//	static int tCnt;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		nums = new int[m];
//		tCnt = 0;

		comb(0, 1);
//		System.out.println("총 개수 : " + tCnt);
	}

	static void comb(int cnt, int start) {

		if (cnt == m) {
//			tCnt++;
			for(int num : nums) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}

		for (int i = start; i <= n; i++) {
			nums[cnt] = i;
			comb(cnt + 1, i + 1);
		}

	}

}
