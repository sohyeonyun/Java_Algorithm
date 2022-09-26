import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 정수 삼각형 - DP 
 */

public class BOJ_1932 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] tri = new int[N][];
		for (int i = 0; i < N; i++) {
			tri[i] = new int[i + 1];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] ans = Arrays.copyOf(tri[N - 1], N);
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				ans[j] = Math.max(ans[j], ans[j + 1]) + tri[i][j];
			}
		}

		System.out.println(ans[0]);
	}

}
