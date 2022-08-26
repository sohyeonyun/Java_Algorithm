import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 녹색 옷 입은 애가 젤다지? - 수정하기~~~~~~~~~~~
 */
public class BOJ_4485 {

	static int N;
	static int[][] map;
	static int[][] v;
	static int result;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = 1;

		while (true) {
			result = Integer.MAX_VALUE;

			// 입력
			N = Integer.parseInt(br.readLine());
			if (N == 0) { // 입력 종료
				break;
			}
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 현재 위치까지의 최소 금액 저장 & 방문 체크
			v = new int[N][N];
			bfs();

			sb.append("Problem ").append(T).append(": ").append(v[N - 1][N - 1]).append("\n");
			T++;
		}

		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(0, 0, map[0][0]));
		v[0][0] = map[0][0];

		while (!q.isEmpty()) {
			Data d = q.poll();

			// 최종 목적지에 도달
			if (d.x == N - 1 && d.y == N - 1) {
				continue;
			}

			// 사방위
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				// 맵 범위 체크
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				// 금액 비교.. 처음 방문이 아니고, 이미 더 작은 금액이 저장돼있으면 패스
				if (v[nx][ny] != 0 && v[nx][ny] <= v[d.x][d.y] + map[nx][ny]) {
					continue;
				}
				// 방문체크 큐
				v[nx][ny] = v[d.x][d.y] + map[nx][ny];
				q.add(new Data(nx, ny, v[nx][ny]));
			}
		}

	}

	static class Data {
		int x, y, sum;

		public Data(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}

}
