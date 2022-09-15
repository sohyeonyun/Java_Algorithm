import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 웜홀 - 플로이드 워셜 
 */
public class BOJ_1865 {

	static int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			// 간선 정보 저장할 인접행렬
			int[][] map = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) { // 자기 자신으로는 최소 비용 0
						map[i][j] = 0;
						continue;
					}
					map[i][j] = INF;
				}
			}

			// 도로 입력받기
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[a][b] = Math.min(map[a][b], c); // 도로가 여러 개
				map[b][a] = Math.min(map[b][a], c);
			}

			// 웜홀 입력받기
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[a][b] = Math.min(map[a][b], -c); // 단방향
			}

			// 플로이드 워셜
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					}
				}
			}

			// 자기 자신으로 가는 비용
			boolean flag = false;
			for (int i = 1; i <= N; i++) {
				if (map[i][i] < 0) {
					flag = true;
					break;
				}
			}
			sb.append(flag == true ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb);
	}

}
