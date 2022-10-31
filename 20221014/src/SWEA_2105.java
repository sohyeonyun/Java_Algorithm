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
	static boolean[] v;
	static int max = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = -1;

			v = new boolean[101]; // 디저트 1~100 번
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					v[map[i][j]] = true;
					dfs(i, j, i, j, 1, 0);
					v[map[i][j]] = false;
				}
			}

			sb.append("#").append(t).append(" ").append(max).append("\n");

		}
		System.out.print(sb);
	}

	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };

	private static void dfs(int startX, int startY, int x, int y, int cnt, int d) {

		for (int i = d; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			// 출발지로 돌아간다면
			if (startX == nx && startY == ny && cnt > 2) {
				max = Math.max(max, cnt);
				return;
			}
			// 이미 먹은 디저트
			if (v[map[nx][ny]]) {
				continue;
			}
			v[map[nx][ny]] = true;
			dfs(startX, startY, nx, ny, cnt + 1, i);
			v[map[nx][ny]] = false;
		}
	}

}