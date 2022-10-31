import java.util.Scanner;
import java.util.Stack;

/*
 * 벽돌 깨기
 */
public class Solution {

	static int N;
	static int W, H;
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

	private static boolean go(int[][] map, int cnt) {

		int res = getRemain(map);
		if (res == 0) {
			min = 0;
			return true;
		}
		if (cnt == N) {
			min = Math.min(min, res);
			return false;
		}

		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; c++) {
			int r = 0;
			// 시작 위치 찾기
			while (r < H && map[r][c] == 0) {
				r++;
			}
			if (r == H) {
				continue; /////////////// !!!!!
			}

			copy(newMap, map);

			boom(newMap, r, c, newMap[r][c]);

			down(newMap);

			if(go(newMap, cnt + 1)) {
				return true;
			}
		}

		return false;
	}

	static Stack<Integer> stack = new Stack<>();

	private static void down(int[][] map) {

		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (map[r][c] > 0) {
					stack.push(map[r][c]);
					map[r][c] = 0;
				}
			}
			int nr = H -1 ;
			while(!stack.isEmpty()) {
				map[nr--][c] = stack.pop();
			}
		}

	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	private static void boom(int[][] newMap, int r, int c, int cnt) {

		newMap[r][c] = 0;
		if (cnt == 1) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r;
			int nc = c;

			for (int k = 1; k < cnt; k++) {
				nr += dr[i];
				nc += dc[i];

				if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
					continue;
				}
				if (newMap[nr][nc] == 0) {
					continue;
				}
				boom(newMap, nr, nc, newMap[nr][nc]);
			}
		}

	}

	private static void copy(int[][] newMap, int[][] map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

	private static int getRemain(int[][] map) {
		int sum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0) {
					sum++;
				}
			}
		}
		return sum;
	}

}
