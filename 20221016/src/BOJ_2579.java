import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 계단 오르기
 */
public class BOJ_2579 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[301];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[301];
		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];
		for (int i = 3; i <= N; i++) {
			// 1. 2칸 점프 (전전 계단 + 현재칸)
			//		dp[i - 2] + arr[i]
			// 2. 1칸 점프 (전 계단 + 현재칸 + 전전전 최대)
			//		dp[i - 3] + arr[i - 1] + arr[i] 
			dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);
		}

		System.out.println(dp[N]);
	}

}
