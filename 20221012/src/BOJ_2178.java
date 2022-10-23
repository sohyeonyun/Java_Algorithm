import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 미로 탐색
 */
public class BOJ_2178 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		bfs();
		
		System.out.println(map[N-1][M-1]);
	}

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	private static void bfs() {

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			// 도착지
			if(cur.x == N - 1 && cur.y == M -1 ) {
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				// 범위 체크
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				// 이미 방문한 칸
				// 이동할 수 없는 칸
				if(map[nx][ny] != 1 || map[nx][ny] == 0) {
					continue;
				}
				// 큐 삽입, 방문 체크
				q.offer(new Point(nx, ny));
				map[nx][ny] += map[cur.x][cur.y];
			}
			
		}
		
		
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
