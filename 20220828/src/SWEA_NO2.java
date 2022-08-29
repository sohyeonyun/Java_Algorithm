import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 헌터 - 순열
 */
public class SWEA_NO2 {

	static int N, no;
	static int[][] map;
	static Point[] monsters, customers;
	static boolean[] monsterV, v;
	static Point[] order;
	static int MIN;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			MIN = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine()); // 맵 길이
			map = new int[N][N];

			monsters = new Point[4]; // 몬스터, 고객 저장할 배열
			customers = new Point[4];
			no = 0; // 몬스터와 고객의 수

			// 맵 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 양수 몬스터, 음수 고객
					if (map[i][j] > 0) {
						int idx = map[i][j] - 1;
						monsters[idx] = new Point(i, j);
						no++; // 몬스터 개수 체크
					} else if (map[i][j] < 0) {
						int idx = -map[i][j] - 1;
						customers[idx] = new Point(i, j);
					}
				}
			}

			monsterV = new boolean[no]; // 몬스터 처리했는지 방문 체크
			v = new boolean[no * 2]; // 몬스터+고객 방문 체크
			order = new Point[no * 2]; // 순열 결과 저장

			// (no * 2)! 경우의 수를 구함.
			perm(0);
			
			// 출력
			sb.append("#").append(t).append(" ").append(MIN).append("\n");
		}
		System.out.print(sb);
	}

	private static void perm(int cnt) {
		// 다 뽑음
		if (cnt == no * 2) {
			MIN = Math.min(MIN, getDistance());
			return;
		}

		for (int i = 0; i < no * 2; i++) {
			// 순열로 뽑혔으면 패스
			if (v[i]) {
				continue;
			}
			// 고객을 선택할 때, 몬스터가 아직 안잡혔으면 패스
			if (i >= no && !monsterV[i - no]) {
				continue;
			}
			// 순열 방문체크
			v[i] = true;
			if (i < no)
				monsterV[i] = true;
			// 순서 저장
			order[cnt] = (i < no) ? monsters[i] : customers[i - no];
			// 재귀
			perm(cnt + 1);
			// 방문 체크 해제
			v[i] = false;
			if (i < no)
				monsterV[i] = false;
		}
	}

	private static int getDistance() {
		int d = 0;
		int x = 0, y = 0; // 헌터 시작 위치
		int nx, ny; // 다음 위치
		for (int i = 0; i < order.length; i++) {
			nx = order[i].x;
			ny = order[i].y;
			d += Math.abs(x - nx) + Math.abs(y - ny);
			x = nx;
			y = ny;
		}
		return d;
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
