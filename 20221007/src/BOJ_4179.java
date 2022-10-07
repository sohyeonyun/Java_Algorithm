import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 불!
 */

public class BOJ_4179 {

	static int R, C;
	static char[][] map;
	static Queue<Point> jihunQ = new LinkedList<>();
	static Queue<Point> fireQ = new LinkedList<>();
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'J') {
					jihunQ.offer(new Point(i, j));
				} else if (map[i][j] == 'F') {
					fireQ.offer(new Point(i, j));
				}
			}
		}

		int res = simulate();
		System.out.println(res == -1 ? "IMPOSSIBLE" : res);

	}

	private static int simulate() {

		int time = 0;

		int size, nr, nc;
		Point cur;
		while (!jihunQ.isEmpty()) {
			time++;

			size = fireQ.size();
			while (size-- > 0) {
				cur = fireQ.poll();
				for (int i = 0; i < 4; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
						continue;
					}
					if (map[nr][nc] == '.' || map[nr][nc] == 'J') {
						fireQ.offer(new Point(nr, nc));
						map[nr][nc] = 'F';
					}
				}
			}

			size = jihunQ.size();
			while (size-- > 0) {
				cur = jihunQ.poll();
				for (int i = 0; i < 4; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
						return time;
					}
					if (map[nr][nc] == '.') {
						jihunQ.offer(new Point(nr, nc));
						map[nr][nc] = 'J';
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
