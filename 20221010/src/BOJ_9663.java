import java.util.Scanner;

/*
* N-Queen
*/

public class BOJ_9663 {

	static int N;
	static int ans = 0;
	static boolean[][] v;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		v = new boolean[N][N];

		dfs(0, 0);

		System.out.println(ans);
	}

	private static void dfs(int cnt, int r) {

		if (cnt == N) {
			ans++;
			return;
		}

		for (int j = 0; j < N; j++) {
			if (isAble(r, j)) {
				v[r][j] = true;
				dfs(cnt + 1, r + 1);
				v[r][j] = false;
			}
		}

	}

	private static boolean isAble(int r, int c) {
		// 같은 열에 있는지
		for (int i = 0; i < r; i++) {
			if (v[i][c]) {
				return false;
			}
		}
		// 대각선에 있는지
		for (int i = 1; i <= r; i++) {
			int left = c - i;
			int right = c + i;
			if (left >= 0 && v[r - i][left]) {
				return false;
			}
			if (right < N && v[r - i][right]) {
				return false;
			}
		}
		return true;
	}

}
