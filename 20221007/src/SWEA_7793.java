import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 악마의 손아귀
 */
public class SWEA_7793 {

	static int N, M;
	static char[][] map;
	static Queue<Point> suQ;
	static Queue<Point> devilQ;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			suQ = new LinkedList<>(); // 초기화 꼭좀해라잉
			devilQ = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					// 시작점, 악마 체크
					if (map[i][j] == 'S') {
						suQ.offer(new Point(i, j));
					} else if (map[i][j] == '*') {
						devilQ.offer(new Point(i, j));
					}
				}
			}

			int res = simulate();

			sb.append("#").append(t).append(" ").append(res == -1 ? "GAME OVER" : res).append("\n");
		}

		System.out.print(sb);

	}

	private static int simulate() {

		int time = 0;

		// 수연이가 이동할 곳 없을 때까지
		while (!suQ.isEmpty()) {
			time++;

			// 악마 이동
			int size = devilQ.size();
			while (size-- > 0) {
				Point devil = devilQ.poll();
				for (int i = 0; i < 4; i++) {
					int nr = devil.r + dr[i];
					int nc = devil.c + dc[i];
					// 맵 범위 체크
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
						continue;
					}
					// 빈칸이나 수연이 있던 자리면 이동 가능
					if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
						devilQ.offer(new Point(nr, nc));
						map[nr][nc] = '*'; // 방문 체크
					}
				}
			}

			// 수연 이동
			size = suQ.size();
			while (size-- > 0) {
				Point su = suQ.poll();
				for (int i = 0; i < 4; i++) {
					int nr = su.r + dr[i];
					int nc = su.c + dc[i];
					// 맵 범위 벗어나거나 돌이면 패스
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
						continue;
					}
					// 여신에게 도착!
					if (map[nr][nc] == 'D') {
						return time;
					}
					// 빈칸이면 이동가능
					if (map[nr][nc] == '.') {
						suQ.offer(new Point(nr, nc));
						map[nr][nc] = 'S'; // 방문체크
					}
				}
			}

		}

		return -1;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}

}
