import java.util.Scanner;

/*
 * 키순서- DFS_Memoization
 */
public class SWEA_5643_DFS3 {
	static int N, M;
	static int res;
	static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int t = 1; t <= TC; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			int[][] map = new int[N + 1][N + 1]; // 1씩 더 크게 잡은 0첨자 버림

			// 메모이제이션을 위한 초기값 설정
			for (int i = 1; i <= N; i++) {
				map[i][0] = -1;
			}
			for (int i = 0; i < M; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				map[from][to] = 1;
			}
			res = 0;
			for (int i = 1; i <= N; i++) {
				if (map[i][0] == -1) { // 이미 탐색된 애들은 탐색하지 않음
					dfs(i, map);
				}
			}
			// 모든 정점이 알고 있는 관계로 탐색 마침(큰정점을 따라 탐색해서 간접관계를 직접관계로 반영
			// 열 기준을 정보를 확인하면 자신보다 작은 정점을 파악이 가능해짐
			for (int j = 1; j <= N; j++) {
				int cnt = 0;
				for (int i = 1; i <= N; j++) {
					cnt += map[i][j];
				}
				map[0][j] = cnt;
			}
			// 0행과 0열을 더해서 N-1이 나오면 모든 정보를 아는 학생
			for (int i = 1; i <= N; i++) {
				if (map[0][i] + map[i][0] == N - 1) {
					res++;
				}
			}

			System.out.println("#" + t + " " + res);
		}
	}

	static void dfs(int cur, int[][] map) { // start 학생부터 자신보다 키가 크거나 작거나 학생따라 탐색

		for (int i = 1; i <= N; i++) {
			if (map[cur][i] == 1) {
				if (map[i][0] == -1) { // 아직 탐색이 안된 경우 재귀
					dfs(i, map);
				}
				// 나보다 큰 정점의 탐색 정보를 메모함(이 부분이 프로이드 와샬)
				if (map[i][0] > 0) { // i보다 큰 정점이 존재 cur < i < j
					for (int j = 1; j <= N; j++) {
						if (map[i][j] == 1) { // 새롭게(i를 통해) 알게된 정보를 cur의 정보로 갱신
							map[cur][j] = 1;
						}
					}
				}
			}
		}
		// cur보다 큰 정점의 탐색 완료했다면 0열에 메모
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += map[cur][i]; // 연결이 알게된 값들 세기
		}
		map[cur][0] = cnt; // 메모
	}
}