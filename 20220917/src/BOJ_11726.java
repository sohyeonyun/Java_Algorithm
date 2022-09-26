import java.util.Scanner;

/*
 * 2 x n 타일링 - DP 
 */

public class BOJ_11726 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;

		for(int i = 2;i <= N;i++){
	        // 2*(n-1) 타일의 뒤에 2*1 한 개 붙이는 경우
	        // +
	        // 2*(n-2) 타일의 뒤에 1*2 두 개 붙이는 경우
			dp[i] = (dp[i - 1] + dp[i-2]) % 10007;
		}

		System.out.println(dp[N]);
	}

}
