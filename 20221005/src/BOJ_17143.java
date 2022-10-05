import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 낚시왕
 */
public class BOJ_17143 {

	static int R, C, M; // 격자판 크기 RC, 상어수M
	static LinkedList<Shark> list = new LinkedList<>(); // 삭제 연산해야하므로 링크드리스트
	static Shark[][] map;
	static int answer = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R + 1][C + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Shark s = new Shark(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			map[r][c] = s;
		}

		// 1초마다 오른쪽으로 한 칸씩 이동함.
		for (int c = 1; c <= C; c++) {
			simulate(c);
		}

		System.out.println(answer);

	}

	private static void simulate(int c) {
		// 낚시
		fishing(c);

		// 상어 이동
		list = new LinkedList<>();
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] != null) {
					move(map[i][j]);
					list.add(map[i][j]);
				}
			}
		}

		// 같은 위치의 상어 죽이기
		kill();
	}

	private static void kill() {

		// 맵 초기화
		map = new Shark[R + 1][C + 1];
		// 리스트 -> 맵
		for (Shark s : list) {
			// 이미 상어 있으면
			if (map[s.r][s.c] != null) {
				// 새로 들어오는 상어가 더 크면 바꿔줌.
				if (s.size > map[s.r][s.c].size) {
					map[s.r][s.c] = s;
				}
			} else {
				map[s.r][s.c] = s;
			}
		}

	}

	// 위 아래 오른쪽 왼쪽
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	private static void move(Shark s) {

		int nr = s.r, nc = s.c;
		int speed;
		// 위아래 이동
		if (s.dir == 1 || s.dir == 2) {
			speed = s.speed % (2 * R - 2);
			for (int i = 1; i <= speed; i++) {
				nr += dr[s.dir];
				if (nr <= 0 || nr > R) {
					s.dir = (s.dir == 1 ? 2 : 1);
					nr += dr[s.dir] * 2;
				}
			}
		} else if (s.dir == 3 || s.dir == 4) { // 좌우 이동
			speed = s.speed % (2 * C - 2);
			for (int i = 1; i <= speed; i++) {
				nc += dc[s.dir];
				if (nc <= 0 || nc > C) {
					s.dir = (s.dir == 3 ? 4 : 3);
					nc += dc[s.dir] * 2;
				}
			}
		}
		// 이동 후 위치 저장
		s.r = nr;
		s.c = nc;
	}

	private static void fishing(int c) {
		for (int i = 1; i <= R; i++) {
			if (map[i][c] != null) {
				answer += map[i][c].size;
				map[i][c] = null;
				return;
			}
		}
	}

	static class Shark {
		int r, c, speed, dir, size;

		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}
	}

}
