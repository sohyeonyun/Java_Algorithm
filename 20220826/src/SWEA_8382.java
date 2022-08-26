import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 방향 전환
 */
public class SWEA_8382 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static Point start, end;
	static int[][] v;
	static int xMin, xMax, yMin, yMax;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()) + 100, Integer.parseInt(st.nextToken()) + 100);
			end = new Point(Integer.parseInt(st.nextToken()) + 100, Integer.parseInt(st.nextToken()) + 100);

			xMin = Math.min(start.x, end.x) + (Math.min(start.x, end.x) == 0 ? 0 : -1);
			xMax = Math.max(start.x, end.x) + (Math.max(start.x, end.x) == 200 ? 0 : 1);
			yMin = Math.min(start.y, end.y) + (Math.min(start.y, end.y) == 0 ? 0 : -1);
			yMax = Math.max(start.y, end.y) + (Math.max(start.y, end.y) == 200 ? 0 : 1);

			v = new int[201][201];
			for (int i = xMin; i <= xMax; i++) {
				for (int j = yMin; j <= yMax; j++) {
					v[i][j] = 999;
				}
			}

			v[start.x][start.y] = 0;
			dfs(start.x, start.y, true);
			dfs(start.x, start.y, false);

			sb.append("#").append(t).append(" ").append(v[end.x][end.y]).append("\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int x, int y, boolean flag) {
		if (x == end.x && y == end.y) {
			return;
		}

		// 이전 탐색 결과에 따라 가로 탐색할지 세로 탐색할지
		int idx = 0;
		if (flag) {
			idx = 2;
		}

		for (int i = idx; i < idx + 2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 맵 벗어나면 패스
			if (nx < xMin || nx > xMax || ny < yMin || ny > yMax) {
				continue;
			}
			// 이미 더 적은 횟수로 방문했으면 패스 .... 이전 탐색 방향알아야햇
			if (v[x][y] + 1 >= v[nx][ny]) {
				continue;
			}
			// 이동 칸 저장
			v[nx][ny] = v[x][y] + 1;
			dfs(nx, ny, !flag);
		}

	}

}
