import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] data = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N][3];
		// 첫번째 집의 RGB 초기화
		for (int i = 0; i < 3; i++) {
			dp[0][i] = data[0][i];
		}
		// DP
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + data[i][j];
			}
		}
		
		int min = Math.min(dp[N-1][0], dp[N-1][1]);
		min = Math.min(min, dp[N-1][2]);
		System.out.println(min);
	}

}
