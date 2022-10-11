import java.util.Scanner;

/*
 * 키순서 
 */
public class SWEA_5643_DFS {
	static int N, M;
	static int[][] map;
	static int res;
	static int gtCnt, ltCnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int t = 1; t <= TC; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N + 1][N + 1]; // 1씩 더 크게 잡은 0첨자 버림
			for (int i = 0; i < M; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				map[from][to] = 1;
			}
			res = 0;
			for (int i = 1; i <= N; i++) {
				gtCnt = 0;
				ltCnt = 0;
				gtDFS(i, new boolean[N + 1]);
				ltDFS(i, new boolean[N + 1]);
				if (gtCnt + ltCnt == N - 1) {
					res++;
				}
			}
			System.out.println("#" + t + " " + res);
		}
	}

	static void gtDFS(int cur, boolean[] v) { // start 학생부터 자신보다 키가 큰 학생따라 탐색
		v[cur] = true;

		for (int i = 1; i <= N; i++) {
			if (v[i]) { // 이미 방문한 좌표 무시
				continue;
			}
			if (map[cur][i] == 1) {
				gtCnt++; // 나보다 큰 애들 수 증가
				gtDFS(i, v);
			}
		}
	}

	static void ltDFS(int cur, boolean[] v) { // start 학생부터 자신보다 키가 작은 학생따라 탐색
		v[cur] = true;
		
		for (int i = 1; i <= N; i++) {
			if (v[i]) { // 이미 방문한 좌표 무시
				continue;
			}
			if (map[i][cur] == 1) {
				ltCnt++; // 나보다 작은 애들 수 증가
				ltDFS(i, v);
			}
		}
	}
}