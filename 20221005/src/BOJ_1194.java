import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 달이 차오른다, 가자!
 * - 빈칸 .
 * - 벽 #
 * - 열쇠 abcdef
 * - 문 ABCDEF
 * - 현재위치 0
 * - 출구 1
 */
public class BOJ_1194 {

	static int N, M;
	static char[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int r = 0, c = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '0') {
					r = i;
					c = j;
					map[i][j] = '.';
				}
			}
		}

		System.out.println(bfs(r, c));
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	private static int bfs(int r, int c) {

		// 열쇠 상태 별로 방문체크 -> 열쇠를 비트마스킹으로 나타낸다.
		// 111111(== 63) -> a~f 다가진 상태
		// 000001 -> 열쇠 a 갖고 있음
		boolean[][][] v = new boolean[N][M][64];

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c, 0, 1));
		v[r][c][0] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			// 4방위
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				int key = cur.key;
				// 범위 체크
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				// 출구면 끝
				if(map[nr][nc] == '1') {
					return cur.cnt;
				}
				// 방문했거나 벽이면 패쓰
				if (v[nr][nc][key] || map[nr][nc] == '#') {
					continue;
				}
				// 열쇠면 챙기고 이동
				else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					int k = 1 << (map[nr][nc] - 'a');
					key |= k;
				}
				// 문이면 열쇠 있을때만 이동 가능
				else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
					int d = 1 << (map[nr][nc] - 'A');
					if ((key & d) != d)
						continue;
				}

				q.offer(new Point(nr, nc, key, cur.cnt + 1));
				v[nr][nc][key] = true;
			}
		}
		
		return -1;

	}

	static class Point {
		int r, c, key, cnt;

		public Point(int r, int c, int key, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
			this.cnt = cnt;
		}
	}

}
