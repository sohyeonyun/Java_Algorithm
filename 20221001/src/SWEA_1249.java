import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1249 {

	static int N;
	static int[][] map, v;
	static int INF = 987654321;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(sc.nextLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(sc.nextLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = sc.nextLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			
			bfs(0, 0);

			sb.append("#").append(t).append(" ").append(v[N - 1][N - 1]).append("\n");
		}

		System.out.println(sb);
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	private static void bfs(int x, int y) {
		Queue<Data> q = new LinkedList<>();
		v = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(v[i], INF);
		}
		
		v[x][y] = 0;
		q.add(new Data(x, y));
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				// 범위 체크
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				// 첫 방문 아니고 이미 짧은 비용으로 방문했으면 패쓰
				if (v[nx][ny] != INF && v[nx][ny] <= v[cur.x][cur.y] + map[nx][ny]) {
					continue;
				}
				// 탐색
				v[nx][ny] = v[cur.x][cur.y] + map[nx][ny];
				q.add(new Data(nx, ny));
			}
		}
	}
	
	static class Data {
		int x, y;

		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
