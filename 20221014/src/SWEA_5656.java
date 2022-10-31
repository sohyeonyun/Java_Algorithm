import java.util.Scanner;
import java.util.Stack;

/*
 * 벽돌깨기
 */
public class SWEA_5656 {

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
		for (int y = 0; y < W; y++) {
			int x = 0;
			while (x < H && map[x][y] == 0) {
				x++;
			}
			if (x == H) {
				continue;
			}

			copy(map, newMap);
			boom(newMap, x, y, newMap[x][y]);
			down(newMap);
			if (go(newMap, cnt + 1)) {
				return true;
			}
		}

		return false;
	}

	private static int getRemain(int[][] map) {
		int sum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					sum++;
				}
			}
		}
		return sum;
	}

	static Stack<Integer> stack = new Stack<>();

	private static void down(int[][] map) {
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if (map[i][j] != 0) {
					stack.push(map[i][j]);
					map[i][j] = 0;
				}
			}
			int x = H - 1;
			while (!stack.isEmpty()) {
				map[x--][j] = stack.pop();
			}
		}
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	private static void boom(int[][] map, int x, int y, int cnt) {

		map[x][y] = 0;
		if (cnt == 1) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x;
			int ny = y;
			for (int k = 1; k < cnt; k++) {
				nx += dx[i];
				ny += dy[i];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
					continue;
				}
				if (map[nx][ny] != 0) {
					boom(map, nx, ny, map[nx][ny]);
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

}
