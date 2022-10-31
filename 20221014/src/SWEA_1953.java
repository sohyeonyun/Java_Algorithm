import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 탈주범 검거
 */
public class SWEA_1953 {

	static int N, M, R, C, L;
	static int res;
	static int[][] map;
	static boolean[][] v;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] tunnels = {
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{1, 0, 1, 0},
			{0, 1, 0, 1},
			{1, 1, 0, 0},
			{0, 1, 1, 0},
			{0, 0, 1, 1},
			{1, 0, 0, 1},
	};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = 1;
			v = new boolean[N][M];
			
			bfs();
			
			sb.append("#").append(t).append(" ").append(res).append("\n");
			
		}
		System.out.print(sb);
		
	}
	private static void bfs() {

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(R, C, 1));
		v[R][C] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			int[] dir = tunnels[map[cur.x][cur.y]];
			for(int d=0; d<4; d++) {
				if(dir[d] == 0) { // 터널 연결 안돼있으면
					continue;
				}
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || v[nx][ny]) {
					continue;
				}
				if(map[nx][ny] == 0) {
					continue;
				}
				if(tunnels[map[nx][ny]][(d + 2) % 4] == 0) {
					continue;
				}
				if(cur.time == L) {
					continue;
				}
				v[nx][ny] = true;
				q.offer(new Point(nx, ny, cur.time + 1));
				res++;
			}
		}
		
	}
	
	static class Point {
		int x, y, time;

		public Point(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

}
