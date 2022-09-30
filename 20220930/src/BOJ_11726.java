import java.util.Scanner;

/*
 *  2 x n 타일링 - DP
 */

public class BOJ_11726 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] dp = new int[1001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		for (int i = 4; i <= n; i++) {
			dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
		}
		
		System.out.println(dp[n]);

	}

}
