import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2638 {
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int N, M;
	static int[][] map;
	static Queue<Point> list = new LinkedList<>();
	static Queue<Point> removeList = new LinkedList<>();
	static int time = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					list.offer(new Point(i, j));
				}
			}
		}
		
		run();
		
		System.out.println(time);
	}

	private static void run() {
		
		// 겉면 채우기
		fillOutside();
		
		// 치즈 없으면 종료
		while(!list.isEmpty()) {			
			time++;
			
			// 녹는 치즈 찾기 
			findKillCheese();
			
			// 치즈 없애기
			killCheese();
		}
	}

	private static void killCheese() {

		while(!removeList.isEmpty()) {
			Point cur = removeList.poll();

			dfs(cur.x, cur.y, 2);
		}
	}

	private static void findKillCheese() {

		// 모든 치즈 탐색하며 죽일거 찾기
		int size = list.size();
		for(int i=0; i<size; i++) {
			Point cur = list.poll();
			
			// 치즈의 사방위에 공기 접촉 수
			int cnt = 0;
			for(int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if(map[nx][ny] == 2) {
					cnt++;
				}
			}
			
			if(cnt >= 2) { // 죽이기
				removeList.offer(new Point(cur.x, cur.y));
			} else { // 살리기
				list.offer(cur);
			}
		}
	}

	private static void fillOutside() {

		// 왼쪽, 오른쪽 줄 
		for(int i=0; i<N; i++) {
			if(map[i][0] == 0) {
				dfs(i, 0, 2);
			}
			if(map[i][M - 1] == 0) {
				dfs(i, M - 1, 2);
			}
		}
		// 위, 아래 줄 
		for(int j=0; j<M; j++) {
			if(map[0][j] == 0) {
				dfs(0, j, 2);
			}
			if(map[N - 1][j] == 0) {
				dfs(N - 1, j, 2);
			}
		}
		
	}
	
//	private static void debug() {
//
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}

	private static void dfs(int x, int y, int num) {

		map[x][y] = num;
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}
			if(map[nx][ny] == 0) {
				dfs(nx, ny, num);
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
