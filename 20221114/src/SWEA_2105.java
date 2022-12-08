import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 디저트 카페 
 */
public class SWEA_2105 {

	static int N;
	static int[][] map;
	static int ans;
	static int startR, startC;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			// 맵 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = -1;
			v = new boolean[101];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					startR = i;
					startC = j;
					v[map[i][j]] = true;
					dfs(i, j, 0, 1);
					v[map[i][j]] = false;
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.print(sb);
	}

	// 오아, 왼아, 왼위, 오위
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	private static void dfs(int r, int c, int dir, int cnt) {

		// 같은 방향으로 이동
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if (0 <= nr && nr < N && 0 <= nc && nc < N) {
			if (startR == nr && startC == nc) {
				ans = Math.max(ans, cnt);
				return;
			}
			if (!v[map[nr][nc]]) {
				v[map[nr][nc]] = true;
				dfs(nr, nc, dir, cnt + 1);
				v[map[nr][nc]] = false;
			}
		}

		// 다음 방향으로 이동
		if (dir + 1 < 4) {
			nr = r + dr[dir + 1];
			nc = c + dc[dir + 1];
			if (0 <= nr && nr < N && 0 <= nc && nc < N) {
				if (startR == nr && startC == nc) {
					ans = Math.max(ans, cnt);
					return;
				}
				if (!v[map[nr][nc]]) {
					v[map[nr][nc]] = true;
					dfs(nr, nc, dir + 1, cnt + 1);
					v[map[nr][nc]] = false;
				}
			}
		}

	}

}
