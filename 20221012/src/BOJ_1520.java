import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 내리막길 - DP + DFS
 */
public class BOJ_1520 {

	static int N, M;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 방문 안함을 -1로 초기화
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(dfs(0, 0));

	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	private static int dfs(int r, int c) {

		// 도착
		if (r == N - 1 && c == M - 1) {
			return 1;
		}
		// 이미 방문 했으면
		if(dp[r][c] != -1) {
			return dp[r][c];
		}

		// 방문 처리
		dp[r][c] = 0;
		// 사방위 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 범위 체크
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			// 내리막길 아니면 패스
			if (map[r][c] <= map[nr][nc]) {
				continue;
			}
			// dp 업데이트 - 현재 위치에서 도착지까지 갈 수 있는 경로 수
			dp[r][c] += dfs(nr, nc);
		}
		
		return dp[r][c]; // 이부분이 이전으로 타고타고 넘겨주는 부분!
	}

}
