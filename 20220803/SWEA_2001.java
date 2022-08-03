import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001 {
	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			System.out.printf("#%d %d%n", i, sol());
		}
	}

	// 누적합
	private static int sol() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력 받기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] sum = new int[n + 1][n + 1];
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n + 1; j++) {
				int input = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + input;
			}
		}

		int MAX = 0;
		// (i, j) 위치를 왼쪽 위로 두고 m * m 파리채 내려친다
		for (int i = 1; i <= n - m + 1; i++) {
			for (int j = 1; j <= n - m + 1; j++) {
				// 누적합 이용해 파리 개수 구한다.
				int s = sum[i + m - 1][j + m - 1] - sum[i + m - 1][j - 1] - sum[i - 1][j + m - 1] + sum[i - 1][j - 1];
				MAX = Math.max(MAX, s);
			}
		}

		return MAX;
	}

	// 브루트 포스
//	private static int sol() throws IOException {
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		// 입력 받기
//		int n = Integer.parseInt(st.nextToken());
//		int m = Integer.parseInt(st.nextToken());
//		int[][] map = new int[n][n];
//		for (int i = 0; i < n; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < n; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//
//		int MAX = 0;
//		// (i, j) 위치를 왼쪽 위로 두고 m * m 파리채 내려친다
//		for (int i = 0; i <= n - m; i++) { 
//			for (int j = 0; j <= n - m; j++) {
//				int sum = 0;
//				// 파리채
//				for (int k = i; k < i + m; k++) {
//					for (int s = j; s < j + m; s++) {
//						sum += map[k][s];
//					}
//				}
//				MAX = Math.max(MAX, sum);
//			}
//		}
//
//		return MAX;
//	}

}
