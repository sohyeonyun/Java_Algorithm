import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 핀볼 게임 
 */
public class SWEA_5650 {

	static int[][] map;
	static int N, max;
	static Point[][] worms;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			worms = new Point[11][2];
			max = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 웜홀 저장 
					if(6 <= map[i][j] && map[i][j] <= 10) {
						if(worms[map[i][j]][0] == null) {
							worms[map[i][j]][0] = new Point(i, j);
						} else {
							worms[map[i][j]][1] = new Point(i, j);
						}
					}
				}
			}
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 빈 칸에서 사방위로 출발
					if (map[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							max = Math.max(max, game(i, j, d));
						}
					}
				}
			}

			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}

	// 상우하좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	// 블록
	static int[][] blocks = { 
			{}, 
			{ 2, 3, 1, 0 }, 
			{ 1, 3, 0, 2 }, 
			{ 3, 2, 0, 1 }, 
			{ 2, 0, 3, 1 }, 
			{ 2, 3, 0, 1 }, 
	};

	private static int game(int sx, int sy, int sd) {

		int x = sx;
		int y = sy;
		int d = sd;
		int sum = 0;
		while (true) {
			x += dx[d];
			y += dy[d];
			// 벽에 부딪히면 어차피 돌아오니까 점수 2배하고 종료
			if (x < 0 || x >= N || y < 0 || y >= N) {
				return sum * 2 + 1;
			}
			// 블랙홀 혹은 시작점이면 종료
			if (map[x][y] == -1 || (x == sx && y == sy)) {
				return sum;
			}
			// 빈 칸이면 계속 이동
			if (map[x][y] == 0) {
				continue;
			}
			// 블록 만나면
			else if ( 1 <= map[x][y] && map[x][y] <= 5) {
				sum += 1;
				d = blocks[map[x][y]][d];
			}
			// 웜홀 만나면 
			else {
				Point[] worm = worms[map[x][y]];
				// 첫번째 웜 위치면 두번째 웜으로 이동 
				if (worm[0].x == x && worm[0].y == y) {
					x = worm[1].x;
					y = worm[1].y;
				} else {
					x = worm[0].x;
					y = worm[0].y;
				}
			}
		}

	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
