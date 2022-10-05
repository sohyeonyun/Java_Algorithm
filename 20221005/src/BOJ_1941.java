import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 소문난 칠공주
 */
public class BOJ_1941 {

	static int ans = 0;
	static char[][] map;
	static int[] selected = new int[7];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 7 자리 뽑는 조합
		comb(0, 0, 0);

		System.out.println(ans);
	}

	private static void comb(int cnt, int start, int sCnt) {
		// 도연파가 더 많은 경우 리턴
		if (cnt - sCnt > 3)
			return;

		// 다 뽑으면 멈춤
		if (cnt == 7) {
			// 인접한 자리인지 확인
			bfs(selected[0] / 5, selected[0] % 5);
			return;
		}

		// 조합
		for (int i = start; i < 25; i++) {
			selected[cnt] = i;
			comb(cnt + 1, i + 1, (map[i / 5][i % 5] == 'S' ? sCnt + 1 : sCnt));
		}

	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	private static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		boolean[] v = new boolean[7];
		q.offer(new Point(r, c));
		v[0] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
					continue;
				}
				// 조합 중에 있는지 확인
				int next = 5 * nr + nc;
				for (int k = 0; k < 7; k++) {
					// 다음 좌표가 방문하지 않았고 뽑혀 있다면 체크 
					if (!v[k] && selected[k] == next) {
						v[k] = true;
						cnt++;
						q.offer(new Point(nr, nc));
					}
				}
			}
		}
		// 7개 모두 붙어 있으면
		if (cnt == 7) {
			ans++;
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

}
