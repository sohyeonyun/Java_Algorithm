import java.util.Scanner;

/*
 * 타일 채우기 3 - 2*1, 1*2, 1*1
 */

public class BOJ_14852 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		long[] dp = new long[1_000_001];
		dp[0] = 1;
		dp[1] = 2;
		dp[2] = 7;
		dp[3] = 7 * 2 + 2 * 3 + 2;
		long sum = dp[0] + dp[1] + dp[2] + dp[3];
		for (int i = 4; i <= N; i++) {
			// 이전꺼에 세로타일 붙임(2가지) + 전전꺼에 가로타일 붙임(3가지) + 전전전꺼에 지그재그모양 붙임(2가지) + ... 전전전꺼
			dp[i] = (sum * 2 + dp[i - 2]) % 1_000_000_007;
			sum += dp[i];
		}
		
		System.out.println(dp[N]);
		
	}
	
	// dp[i] = dp[i-1] * 2 + dp[i-2] * 3 + dp[i-3] * 2 + ... + dp[0] * 2;

}
