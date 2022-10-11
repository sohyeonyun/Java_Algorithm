import java.util.Scanner;

/*
 * 키 순서 - 역인접행렬 
 */
public class SWEA_5643_DFS2 {
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
			int[][] rmap = new int[N + 1][N + 1]; // 1씩 더 크게 잡은 0첨자 버림
			for (int i = 0; i < M; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				// 공간 복잡도를 올려서 소스를 단순화 한다.
				rmap[to][from] = map[from][to] = 1;
			}
			res = 0;
			for (int i = 1; i <= N; i++) {
				cnt = 0;
				dfs(i, map, new boolean[N + 1]);
				dfs(i, rmap, new boolean[N + 1]);
				if (cnt == N - 1) {
					res++;
				}
			}
			System.out.println("#" + t + " " + res);
		}
	}

	static void dfs(int cur, int[][] map, boolean[] v) { // start 학생부터 자신보다 키가 크거나 작거나 학생따라 탐색
		v[cur] = true;

		for (int i = 1; i <= N; i++) {
			if (v[i]) { // 이미 방문한 좌표 무시
				continue;
			}
			if (map[cur][i] == 1) {
				cnt++; // 나보다 크거나 작은 애들 수 증가
				dfs(i, map, v);
			}
		}
	}
}