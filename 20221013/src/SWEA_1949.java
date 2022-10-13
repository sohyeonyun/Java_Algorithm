import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 등산로 조성
 */
public class SWEA_1949 {

	static int max;
	static int N;
	static int[][] map;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			max = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			int top = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[i][j]); // 가장 높은 봉우리 높이
				}
			}

			// 가장 높은 봉우리 구함
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (top == map[i][j]) {
						v[i][j] = true;
						go(i, j, map[i][j], K, 1);
						v[i][j] = false;
					}
				}
			}

			System.out.println("#" + t + " " + max);
		}

	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	private static void go(int x, int y, int height, int k, int len) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 범위, 방문 체크
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || v[nx][ny]) {
				continue;
			}
			
			// 높이 같거나 오르막이면
			if (height <= map[nx][ny]) { // 이전에 깎아서 높이가 변한걸 height로 넘겨받음!!
				// 깎아서 이동
				if (height > map[nx][ny] - k) {
					v[nx][ny] = true;
					go(nx, ny, height - 1, 0, len + 1); // 1칸 깎는게 이득
					v[nx][ny] = false;
				} else {
					// 이미 깎아서 더이상 못감 - 최댓값 갱신 후 패스
					max = Math.max(max, len);
					continue;
				}
			} else { // 내리막이면 재귀
				v[nx][ny] = true;
				go(nx, ny, map[nx][ny], k, len + 1);
				v[nx][ny] = false;
			}

		}

	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}

