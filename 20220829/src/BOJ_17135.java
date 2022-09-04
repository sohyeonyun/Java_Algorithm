import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 캐슬 디펜스 - 아,,,왜안돼,,,
 */

public class BOJ_17135 {

	static int N, M, D;
	static int[][] map, copyMap;
	static int[] position;
	static int MAX = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
			}
		}

		position = new int[3];

		// mC3
		comb(0, 0);

		System.out.println(MAX);
	}

	private static void comb(int cnt, int start) {

		if (cnt == 3) {
			play();
			// 되돌리기
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.copyOf(copyMap[i], M);
			}
			return;
		}

		for (int i = start; i < M; i++) {
			position[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	// 좌 상 우 (왼쪽부터 공격한다는 문제 조건)
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };

	private static void play() {
		
		ArrayList<Point> removeList;

		int r = N - 1;

		// 적들 한칸씩 전진 
		while(r >= 0) {
			removeList = new ArrayList<>();
			
			// 궁수 세 명이 한 발씩 쏜다. 
			for (int k = 0; k < 3; k++) {
				// 궁수 위치 
				int x = r + 1;
				int y = position[k];
				// 왼쪽부터 위로 올라가며 쏜다.
				LOOP: for (int i = r; i >= 0; i--) {
					// 거리 가능한지 
					if(x - i > D) {
						break;
					}
					for (int j = 0; j < M; j++) {
						// 거리 가능한지 
						if(Math.abs(x - i) + Math.abs(y - j) > D) {
							break;
						}
						// 적 있으면 삭제 리스트에 넣음  
						if(map[i][j] == 1) {
							removeList.add(new Point(i, j));
							break LOOP;
						}
					}
				}
			}
			
			// 적 죽이기 
			
			
			
			// 한칸 전진 
			r--;
		}
		
		

	}
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
