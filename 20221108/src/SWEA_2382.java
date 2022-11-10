import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 미생물 
 */
public class SWEA_2382 {
	
	static int N, M, K;
	static Queue<Point> q;
	static Point[][] map;

	// 상하좌우 0123
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			q = new LinkedList<>();
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1; // 1234 -> 0123
				q.offer(new Point(r, c, n, d, n, n));
			}
			
			int ans = simulate();
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	private static int simulate() {

		// M번 이동시키기
		for(int i=0; i<M; i++) {
			map = new Point[N][N];
			// 군집 이동시키기
			move();
			// 이차원배열 군집을 큐로 담기
			putToQueue();
//			debug();
		}
		
		// 남은 미생물 수
		return getRemain();
	}


//	private static void debug() {
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				if(map[i][j] == null) {
//					System.out.print("-");
//				} else {
//					System.out.print(map[i][j].n);
//				}
//			}
//			System.out.println();
//		}
//	}

	private static int getRemain() {
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != null) {
					sum += map[i][j].n;
				}
			}
		}
		
		return sum;
	}
	
	private static void putToQueue() {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != null) {
					map[i][j].n = map[i][j].sum;
					map[i][j].max = map[i][j].sum;
					q.offer(map[i][j]);
				}
			}
		}
	}

	private static void move() {
		
		int size = q.size();
		
		while(size-- > 0) {
			Point cur = q.poll();
			
			int nr = cur.r + dr[cur.d];
			int nc = cur.c + dc[cur.d];
			
			// 약품이 칠해진 셀에 도착
			boolean flag = false;
			if(nr == 0) {
				cur.d = 1; // 이동 방향 변경
				flag = true;
			} else if(nr == N - 1) {
				cur.d = 0;
				flag = true;
			} else if(nc == 0) {
				cur.d = 3;
				flag = true;
			} else if(nc == N - 1) {
				cur.d = 2;
				flag = true;
			}

			// 약품 칠해졌으면
			if(flag) {
				cur.n /= 2; // 미생물 반이 죽음
				cur.max /= 2;
				cur.sum /= 2;
			}
			
			// 이동
			cur.r = nr;
			cur.c = nc;
			
			// 이동 위치에 이미 있으면 합침
			if(map[cur.r][cur.c] != null) {
				Point pre = map[cur.r][cur.c];
				// 최대 군집의 방향으로 저장
				if(pre.max < cur.max) {
					pre.max = cur.max;
					pre.d = cur.d;
				}
				// 합침
				pre.sum += cur.n;
				// 맵에 저장
				map[cur.r][cur.c] = pre; 
			} else {
				// 맵에 저장
				map[cur.r][cur.c] = cur;
			}
		}
		
	}

	static class Point {
		int r, c, n, d, max, sum;

		public Point(int r, int c, int n, int d, int max, int sum) {
			super();
			this.r = r;
			this.c = c;
			this.n = n;
			this.d = d;
			this.max = max;
			this.sum = sum;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", n=" + n + ", d=" + d + ", max=" + max + ", sum=" + sum + "]";
		}
		
	}

}
