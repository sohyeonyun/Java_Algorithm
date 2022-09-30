import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * 말이 되고픈 원숭이 - BFS
 */
public class BOJ_1600 {

	static int K, W, H;
	static int[][] map;
	static int res = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		System.out.println(res);
	}

	static int[] dx = { 1, -1, 0, 0 }; // 원숭이 이동 위치
	static int[] dy = { 0, 0, 1, -1, };
	static int[] hdx = { 1, 2, 2, 1, -1, -2, -2, -1 }; // 말의 이동 위치
	static int[] hdy = { -2, -1, 1, 2, 2, 1, -1, -2 };

	private static void bfs() {
		// 큐
		Queue<Data> q = new LinkedList<>();
		// 시작점
		q.add(new Data(0, 0, 0, 0));
		// 방문체크 (3차원 배열 - 위치 + 말 몇번 타고 왔는지)
		boolean[][][] v = new boolean[K + 1][H][W]; // 말로 한 번도 안바뀌는 경우까지 0 ~ K
		v[0][0][0] = true;

		Data cur;
		// 큐 빌 때까지
		while (!q.isEmpty()) {
			// 큐에서 한 개 뽑기
			cur = q.poll();
			// 할 일하기 = 현재 좌표가 목표 좌표인가 판단
			if (cur.x == H - 1 && cur.y == W - 1) {
				res = cur.cnt; // bfs라서 min 체크 필요X
				return;
			}
			// 탐색 - 원슝
			int nx, ny;
			for (int d = 0; d < 4; d++) {
				nx = cur.x + dx[d];
				ny = cur.y + dy[d];
				// 범위 체크
				if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
					continue;
				}
				// 방문 체크, 장애물 체크
				if(v[cur.k][nx][ny] || map[nx][ny] == 1) {
					continue;
				}
				// 큐
				q.offer(new Data(nx, ny, cur.cnt + 1, cur.k));
				v[cur.k][nx][ny] = true;
			}
			// 탐색 - 말
			if(cur.k < K) {
				for (int d = 0; d < 8; d++) {
					nx = cur.x + hdx[d];
					ny = cur.y + hdy[d];
					if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
						continue;
					}
					if(v[cur.k + 1][nx][ny] || map[nx][ny] == 1) {
						continue;
					}
					q.offer(new Data(nx, ny, cur.cnt + 1, cur.k + 1));
					v[cur.k + 1][nx][ny] = true;
				}
			}
		}

	}

	static class Data {
		int x, y;
		int cnt, k; // 이동 횟수, 말이 된 횟수

		public Data(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + ", cnt=" + cnt + ", k=" + k + "]";
		}
	}

}
