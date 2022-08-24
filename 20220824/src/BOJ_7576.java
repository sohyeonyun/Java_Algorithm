import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 토마토
 * 익은토마토(1), 익지않은토마토(0), 빈칸(-1)
 */
public class BOJ_7576 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int r, c;
	static int[][] map;
	static Queue<Point> tomatoQ = new LinkedList<>();
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int badCount = 0; // 덜익은 토마토 개수
	static int res = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					tomatoQ.add(new Point(i, j));
				else if (map[i][j] == 0)
					badCount++;
			}
		}

		if (badCount == 0) {
			System.out.println(0);
		} else {
			System.out.println(bfs() ? res : -1);
		}
	}

	private static boolean bfs() {

		// 토마토가 다 익을때까지 (익지않은 토마토가 없을 때까지)
		while (badCount > 0) {
			// 안익은 토마토가 있지만 큐에 익은토마토가 더 없으면 확장 불가, 즉 모두 익지 못하는 상황
			if (tomatoQ.isEmpty())
				return false;

			// 하루 지남
			res++;
			// 익은 토마토들 하나씩 꺼내서 인접 위치 익게함
			for (int i = 0, size = tomatoQ.size(); i < size; i++) {
				Point p = tomatoQ.poll();

				// 사방위 탐색
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					// 맵 벗어나면 패스
					if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
						continue;
					}
					// 토마토가 없는 칸이거나 이미 익은 토마토가 있으면 패스
					if (map[nx][ny] == -1 || map[nx][ny] == 1) {
						continue;
					}
					// 토마토 익히기
					map[nx][ny] = 1;
					badCount--;
					tomatoQ.add(new Point(nx, ny));
				}
			}
			
		}

		return true;
	}

}
