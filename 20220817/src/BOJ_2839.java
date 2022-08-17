import java.util.Arrays;
import java.util.Scanner;

/*
 * 설탕 배달 - 그리디 / DP
 */

public class BOJ_2839 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// DP
		if(n < 5) {
			System.out.println(n == 3 ? 1 : -1);
			return;
		}
		
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 9999);
		dp[3] = dp[5] = 1;
		for(int i=6; i<=n; i++) {
			dp[i] = Math.min(dp[i-3] + 1, dp[i-5] + 1);
		}
		System.out.println(dp[n] > 9999 ? -1 : dp[n]);

		// 그리디
//		int cnt = 0;
//		while(true) {
//			if(n % 5 == 0) {
//				cnt += n / 5;
//				System.out.println(cnt);
//				return;
//			} else {
//				n -= 3;
//				cnt++;
//			}
//			
//			if(n < 0) {
//				System.out.println(-1);
//				return;
//			}
//		}

	}

}
