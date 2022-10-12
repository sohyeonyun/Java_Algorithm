import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520 {

	static int N, M;
	static int[][] map, v;

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
		v = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(v[i], -1);
		}

		v[0][0] = 0;
		dfs(0, 0);

		System.out.println(v[N-1][M-1]);

	}

	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	private static boolean dfs(int r, int c) {
		System.out.println(r + " " + c);
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

		// 도착
		if(r == N-1 && c == M-1) {
			return true;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			// 내리막길 아니면 패스
			if(map[r][c] <= map[nr][nc]) {
				continue;
			}
			// 방문체크
			if(v[nr][nc] == -1) {
				v[nr][nc] = 0;
			}
			if(dfs(nr, nc)) { ////////////////////// !!!
				v[nr][nc]++;
			}
		}
		return false;
	}

}
