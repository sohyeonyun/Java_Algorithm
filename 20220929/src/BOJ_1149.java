import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// N개의 집마다 RGB 세가지를 칠하는 경우로 나눠서 저장함.
		int[][] dp = new int[N + 1][3];
		// 초기화
		dp[0][0] = 0;
		dp[0][1] = 0;
		dp[0][2] = 0;
		// N번집까지 색칠해간다.
		for (int i = 1; i <= N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
		}

		// 셋 중에 최솟값이 정답
		int MIN = Math.min(dp[N][0], dp[N][1]);
		MIN = Math.min(MIN, dp[N][2]);
		System.out.println(MIN);
	}

}
