import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * 벽돌깨기
 */

public class SWEA_5656 {

	static int N; // 갯수
	static int W, H; // 맵 넓이 높이
	static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int t = 1; t <= TC; t++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			int[][] map = new int[H][W];

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			} // 맵 데이터 읽기
			min = Integer.MAX_VALUE;
			go(map, 0);
			System.out.println("#" + t + " " + min);
		}
	}

	// 맵 데이터, 몇번째 구슬인지,
	static boolean go(int[][] map, int cnt) { // 구슬 던지기 게임
		
		int res = getRemain(map);
		if(res == 0) { ///////////////////////////////
			min = 0;
			return true; // 더이상 남은 재귀는 호출하는 의미가 없음.
		}
		
		if (cnt == N) { // 모든 구슬을 던진 상태
			min = Math.min(min, res);
			return false;
		}
		
		int[][] newMap = new int[H][W];
		// 중복 순열 구슬 위치 구하기
		for (int x = 0; x < W; x++) {
			// 구슬에 맞는 시작 벽돌 찾기
			int y = 0;
			while (y < H && map[y][x] == 0) { // 경계에 벗어나지 않으면서, 벽돌이 없으면 y증가
				y++;
			}
			if (y == H) { // 시작 벽돌 없는 경우
				continue; ///////////////////////////////
			} else { // 시작 벽돌이 있는 경우
				copy(map, newMap); // 맵 복사(백트래킹 대신 하기)
				// 제거될 벽돌 연쇄 처리
				boom(newMap, y, x);
				// 벽돌 중력 처리
				down(newMap);
				// 다음 구슬 던지기
				if(go(newMap, cnt + 1)) return true; ///////////////////////////////
			}
		}
		
		return false; ///////////////////////////////
	}

	private static int getRemain(int[][] map) { // 0이 아닌 벽돌세기
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static Stack<Integer> stack = new Stack<>();
	
	private static void down(int[][] map) {
		for (int x = 0; x < W; x++) {
			// 윗행부터 남은 벽돌 스택에 넣기
			for(int y = 0; y < H; y++) {
				if(map[y][x] > 0) {
					stack.push(map[y][x]);
					map[y][x] = 0;
				}
			}
			// 남은 벽돌은 스택에 들어있고 모든 칸은 빈칸 상태
			int ny = H - 1;
			while(!stack.isEmpty()) {
                map[ny--][x] = stack.pop();
            }
		}
	}

//	private static void down(int[][] map) {
//		for (int x = 0; x < W; x++) {
//			int y = H - 1; // 가장 아래에서 위로 탐색하기
//			while (y > 0) {
//				if (map[y][x] == 0) {
//					int ny = y - 1;
//					while (ny > 0 && map[ny][x] == 0) {
//						ny--;
//					}
//					map[y][x] = map[ny][x];
//					map[ny][x] = 0;
//				}
//				y--; // 위로 올려주기
//			}
//		}
//	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	private static void boom(int[][] map, int y, int x) { // bfs 방식으로 터트리기
		Queue<Point> q = new LinkedList<>();
		// 방문체크 대신의 맵의 데이터 자체를 0 으로 바꿈으로써 방문한 것으로 처리함(장애물로 만듬)

		// 1 이상의 벽돌값 => 2 이상의 벽돌에 대해서만 작업함
		if (map[y][x] > 1) {
			q.offer(new Point(y, x, map[y][x]));
		}
		map[y][x] = 0;

		Point p;
		while (!q.isEmpty()) {
			p = q.poll();
			// 벽돌의 크기 - 1 만큼 주변(4방위 탐색) 벽돌 연쇄처리
			for (int d = 0; d < 4; d++) {
				int ny = p.y;
				int nx = p.x;
				for (int k = 1; k < p.cnt; k++) { // cnt-1 만큼 처리
					ny = ny + dy[d];
					nx = nx + dx[d];
					if (nx < 0 || nx >= W || ny < 0 || ny >= H) { // 범위 체크
						continue;
					}
					if (map[ny][nx] == 0) { // 빈곳은 멈춤
						continue;
					}
					if (map[ny][nx] > 1) { // 2 이상은 벽돌은 다음큐에 넣어서 작업함
						q.offer(new Point(ny, nx, map[ny][nx]));
					}
					map[ny][nx] = 0;
				}
			}
		}

	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}

	}

	static class Point {
		int y, x;
		int cnt;

		public Point(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

	}

}