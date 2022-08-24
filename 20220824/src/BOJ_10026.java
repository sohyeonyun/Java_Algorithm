import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 적록색약
 */

public class BOJ_10026 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int n;
	static char[][] map;
	static boolean[][] v;
	static int result1 = 0, result2 = 0;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 적록색약 아닌 경우
		v = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (v[i][j])
					continue;
				bfs(i, j, false);
			}
		}

		// 적록색약인 경우
		v = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (v[i][j])
					continue;
				bfs(i, j, true);
			}
		}
		
		System.out.println(result1 + " " + result2);
	}

	private static void bfs(int x, int y, boolean yak) {

		Queue<Point> q = new LinkedList<>();
		// 시작 위치 체크
		q.add(new Point(x, y));
		v[x][y] = true;

		// 시작점 색깔
		char color = map[x][y];
		boolean RG = (color == 'R' || color == 'G');

		while (!q.isEmpty()) {
			Point cur = q.poll();
			// 사방위
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				// 맵 벗어나거나 이미 방문했으면 패스
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || v[nx][ny])
					continue;
				// 적록색약인 경우
				if (yak) {
					// 같은색깔(RG <-> B) 아니면 패스
					boolean B = (map[nx][ny] == 'B');
					if ((RG && B) || (!RG && !B)) {
						continue;
					}
				} else { // 적록색약 아닌 경우
					// 같은 색깔 아니면 패스
					if (color != map[nx][ny])
						continue;
				}
				// 큐에 삽입, 방문체크
				q.add(new Point(nx, ny));
				v[nx][ny] = true;
			}
		}

		if (!yak)
			result1++;
		else
			result2++;
	}

}
