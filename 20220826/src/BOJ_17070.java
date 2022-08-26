import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {

	static int N;
	static int[][] map;
	static int res = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시작 위치, 방향
		dfs(1, 2, 0);

		System.out.println(res);
	}

	// dir: 가로(0), 세로(1), 대각선(2)
	private static void dfs(int y, int x, int dir) {
		if (y == N && x == N) {
			res++;
			return;
		}

		if (dir == 0) { // 가로 -- \
			// 오른쪽
			if (x + 1 <= N && map[y][x + 1] == 0) {
				dfs(y, x + 1, 0);
			}
			// 대각 아래
			if (x + 1 <= N && y + 1 <= N && map[y + 1][x] == 0 && map[y][x + 1] == 0 && map[y + 1][x + 1] == 0) {
				dfs(y + 1, x + 1, 2);
			}
		} else if (dir == 1) { // 세로 | \
			// 아래
			if (y + 1 <= N && map[y + 1][x] == 0) {
				dfs(y + 1, x, 1);
			}
			// 대각 아래
			if (x + 1 <= N && y + 1 <= N && map[y + 1][x] == 0 && map[y][x + 1] == 0 && map[y + 1][x + 1] == 0) {
				dfs(y + 1, x + 1, 2);
			}
		} else { // 대각선 -- | \
			// 오른쪽
			if (x + 1 <= N && map[y][x + 1] == 0) {
				dfs(y, x + 1, 0);
			}
			// 아래
			if (y + 1 <= N && map[y + 1][x] == 0) {
				dfs(y + 1, x, 1);
			}
			// 대각 아래
			if (x + 1 <= N && y + 1 <= N && map[y + 1][x] == 0 && map[y][x + 1] == 0 && map[y + 1][x + 1] == 0) {
				dfs(y + 1, x + 1, 2);
			}
		}
	}

}
