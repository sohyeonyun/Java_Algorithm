import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * LCS 최장 공통 부분 수열 
 */

public class BOJ_9251 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();

		int N = str1.length();
		int M = str2.length();
		// 2차원 배열 dp 
		int[][] dp = new int[N + 1][M + 1];
		// i: 첫번째 문자열 인덱스, j: 두번째 문자열 인덱스 
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 같은 문자면 대각선 위 + 1
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else { // 다른 문자면 왼쪽값과 위쪽값 중 최댓값 
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		System.out.println(dp[N][M]);

	}

}
