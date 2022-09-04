import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 캐슬 디펜스 - 아,,,왜안돼,,,
 */

public class BOJ_17135 {

	static int N, M, D;
	static int copyN;
	static int[][] map, copyMap;
	static int[] position;
	static int[][] remove = new int[3][2];
	static int removeCnt = 0;
	static int MAX = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		copyN = N;

		map = new int[N][M];
		copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
			}
		}

		position = new int[3];

		// mC3
		comb(0, 0);

		System.out.println(MAX);
	}

	private static void comb(int cnt, int start) {

		if (cnt == 3) {
			play();
			N = copyN;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();

			// 되돌리기
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.copyOf(copyMap[i], M);
			}
			N = copyN;
			return;
		}

		for (int i = start; i < M; i++) {
			position[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static void play() {

		int sum = 0;
		while (true) {
			// 게임 끝
			if (N == -1) {
				break;
			}
			removeCnt = 0;
			// (N, c)에 위치한 궁수 세 명 화살 쏘기
			for (int i = 0; i < 3; i++) {
				int c = position[i];
				shoot(N, c, N, c, i);
			}
			// 적 죽이기
			for (int i = 0; i < removeCnt; i++) {
				int r = remove[i][0];
				int c = remove[i][1];
				map[r][c] = 3;
			}
			// 맵 한 줄 밀기
			N--;
			// 죽인 적 개수 세기
			for (int i = 0; i < removeCnt; i++) {
				System.out.println(remove[i][0] + " " + remove[i][1]);
			}
			if(removeCnt == 0) {
				continue;
			}
			for (int i = 0; i < removeCnt; i++) {
				if ((remove[i][0] == remove[(i + 1) % removeCnt][0])
						&& (remove[i][1] == remove[(i + 1) % removeCnt][1])) {
					removeCnt--;
					System.out.println("same");
				}
			}
			if (removeCnt == 0)
				removeCnt = 1;
			System.out.println(removeCnt);
			sum += removeCnt;


		}
		MAX = Math.max(MAX, sum);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(MAX);

	}

	// 좌 상 우 (왼쪽부터 공격한다는 문제 조건)
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };

	private static void shoot(int originR, int originC, int r, int c, int idx) {

		// 3방위
		for (int i = 0; i < 3; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			// 맵 벗어나면 패스, 방문했으면 패스
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 2) {
				continue;
			}
			// 공격 거리 넘어서면 패스
			if (Math.abs(originR - nr) + Math.abs(originC - nc) > D) {
				continue;
			}
			// 적 찾았으면 죽이기
			if (map[nr][nc] == 1) {
				remove[idx][0] = nr;
				remove[idx][1] = nc;
				removeCnt++;
				return;
			}
			map[nr][nc] = 2;
			shoot(originR, originC, nr, nc, idx);
		}

	}

}
