import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 연구소
 */
public class BOJ_14502 {

	static int N, M;
	static int[][] map, newMap;
	static ArrayList<Point> list = new ArrayList<>();
	static Point[] wall = new Point[3];
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		newMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) { // 벽을 세울 수 있는 후보들
					list.add(new Point(i, j));
				}
			}
		}

		comb(0, 0);

		System.out.println(MAX);
	}

	// 벽 세울 위치 조합으로 구하기
	private static void comb(int cnt, int start) {

		if (cnt == 3) {
			// 맵 복사
			for (int i = 0; i < N; i++) {
				newMap[i] = Arrays.copyOf(map[i], M);
			}
			// 뽑힌 세 칸에 벽치기
			for (int i = 0; i < 3; i++) {
				newMap[wall[i].r][wall[i].c] = 1;
			}
			// 바이러스 퍼뜨리기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (newMap[i][j] == 2) {
						dfs(i, j);
					}
				}
			}
			// 안전 영역 개수 확인
			check();
			return;
		}

		for (int i = start; i < list.size(); i++) {
			wall[cnt] = list.get(i);
			comb(cnt + 1, i + 1);
		}
	}

	// 안전영역 개수 확인
	private static void check() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] == 0) {
					sum++;
				}
			}
		}
		MAX = Math.max(MAX, sum);
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	// 바이러스 퍼뜨림
	private static void dfs(int r, int c) {
		// 4방위 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 맵 범위 체크
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			// 바이러스 퍼질 수 있는 공간인지 (빈칸 0) 체크
			if (newMap[nr][nc] != 0) {
				continue;
			}
			// 바이러스
			newMap[nr][nc] = 2;
			dfs(nr, nc);
		}
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
