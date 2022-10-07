import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 키 순서
 */
public class SWEA_5643 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			int N = Integer.parseInt(br.readLine());
			// 간선 표시 - 이차원 행렬
			boolean[][] map = new boolean[N + 1][N + 1];

			int M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = true;
			}

			// 플로이드
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (map[i][k] && map[k][j]) {
							map[i][j] = true;
						}
					}
				}
			}

			int answer = 0;
			// 노드로 들어오는 개수와 나가는 개수의 합이 N-1 이면 키 순서 안다.
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= N; j++) {
					if (map[i][j]) { // 행의 합
						sum++;
					}
					if (map[j][i]) { // 열의 합
						sum++;
					}
				}
				if (sum == N - 1) {
					answer++;
				}
			}

			sb.append("#").append(t).append(" ").append(answer).append("\n");

		}

		System.out.print(sb);

	}

}
