import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 케빈 베이컨의 6단계 법칙
 */

public class BOJ_1389 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N + 1][N + 1];
		
		int INF = 987654321;

		// INF 로 초기화
		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 자기 자신으로의 거리는 0
		for (int i = 1; i <= N; i++) {
			graph[i][i] = 0;
		}

		// 간선 입력 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1; // 양방향
		}

		// 플로이드 워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		// 최솟값 갖는 사람 출력
		int MIN_idx = 0;
		int MIN = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += graph[i][j];
			}
			if (MIN > sum) {
				MIN = sum;
				MIN_idx = i;
			}
		}
		
		System.out.println(MIN_idx);

	}

}
