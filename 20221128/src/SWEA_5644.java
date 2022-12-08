import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 무선 충전 
 */
public class SWEA_5644 {
	
	static int M, bcCnt, totalMax;
	static int[] routeA, routeB;
	static Point playerA, playerB;
	static Charger[] chargers;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			totalMax = 0;
			playerA = new Point(1, 1);
			playerB = new Point(10, 10);
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			bcCnt = Integer.parseInt(st.nextToken());
			
			// 경로 입력 받기 
			routeA = new int[M + 1]; // 0번째에 0이라서 출발지에서 충전량 확인 가능 
			routeB = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<M+1; i++) {
				routeA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<M+1; i++) {
				routeB[i] = Integer.parseInt(st.nextToken());
			}
			
			// 충전기 입력 
			chargers = new Charger[bcCnt];
			for(int i=0; i<bcCnt; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				chargers[i] = new Charger(x, y, c, p);
			}
			
			// 이동하기 
			for(int i=0; i<M+1; i++) {
//				System.out.print(i + " ");
				move(routeA[i], routeB[i]);
			}

			sb.append("#").append(t).append(" ").append(totalMax).append("\n");
		}
		System.out.print(sb);
	}
	
	// 상우하좌 
	static int[] dy = {0, -1, 0, 1, 0};
	static int[] dx = {0, 0, 1, 0, -1};
	private static void move(int dA, int dB) {
		
		playerA.x += dx[dA];
		playerA.y += dy[dA];
		playerB.x += dx[dB];
		playerB.y += dy[dB];

		// 현재 위치에서 충전량 최댓값 
		int max = 0;
		
		for(int i=0; i<bcCnt; i++) {
			for(int j=0; j<bcCnt; j++) {
				int dist = Math.abs(playerA.x - chargers[i].x) + Math.abs(playerA.y - chargers[i].y);
				int a = dist <= chargers[i].coverage ? chargers[i].performance : 0; 
				
				dist = Math.abs(playerB.x - chargers[j].x) + Math.abs(playerB.y - chargers[j].y);
				int b = dist <= chargers[j].coverage ? chargers[j].performance : 0;
				
				// 같은 충전기면 반으로 나눔 
				int sum = (i == j) ? Math.max(a, b) : (a + b);
				
				max = Math.max(max, sum);
			}
		}
		
//		System.out.println("max: " + max);
		
		// 충전량 합해줌 
		totalMax += max;
	}

	static class Charger {
		int x, y, coverage, performance;

		public Charger(int x, int y, int coverage, int performance) {
			super();
			this.x = x;
			this.y = y;
			this.coverage = coverage;
			this.performance = performance;
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
