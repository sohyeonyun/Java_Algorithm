import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 보급로
 */
public class SWEA_1249 {

	static int N = 0, INF = Integer.MAX_VALUE;
	static int map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; ++tc) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				char[] ch = in.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = ch[j] - '0';
				}
			}

			System.out.println("#" + tc + " " + dijkstra(0, 0));
		}

	}

	private static int dijkstra(int startR, int startC) {

		// 출발지에서 자신으로의 최소비용을 저장할 배열 생성 후 초기화
		int[][] minCost = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minCost[i][j] = INF;
			}
		}

		// 출발지에서 출발지로의 최소비용 0 처리
		minCost[startR][startC] = 0;
		int r, c, nr, nc, minTime;

		while (true) {

			// step1. 미방문 정점 중 최소비용 정점 찾기
			r = c = -1;
			minTime = INF;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && minTime > minCost[i][j]) {
						minTime = minCost[i][j];
						r = i;
						c = j;
					}
				}
			}

			// r, c -1일 경우는 더 이상 갈 수 있는 정점이 없다.!
			if (r == -1)
				return -1;
			visited[r][c] = true;
			
			// 도착지
			if (r == N - 1 && c == N - 1)
				return minTime;

			// step2. 현재 정점 기준으로 인접한 정점들 들여다보며 경유비용이 유리한지 계산
			for (int d = 0; d < 4; d++) { // 인접 정점 : 4 방
				nr = r + dr[d];
				nc = c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& minCost[nr][nc] > minTime + map[nr][nc]) {
					minCost[nr][nc] = minTime + map[nr][nc];
				}

			}

		}

	}

}
