
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1861 {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int n;
	static int[][] map;
	static int MAX, value;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			MAX = 0;
			value = Integer.MAX_VALUE;
			// 방크기, 방 입력받기
			n = sc.nextInt();
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// (i, j) 위치에서 시작해 탐색
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int res = search(1, i, j);
					// 최댓값 갱신
					if (MAX < res) {
						MAX = res;
						value = map[i][j];
					} else if(MAX == res) {
						value = Math.min(value, map[i][j]);
					}
				}
			}

			System.out.printf("#%d %d %d%n", t, value, MAX);

		}
	}

	static int search(int cnt, int x, int y) {
		// 상하좌우 방향 탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 맵 벗어나면 안봄.
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
				continue;
			}
			// 다음 방이 현재 방보다 1 더 클 때
			if (map[nx][ny] - map[x][y] == 1) {
				return search(cnt + 1, nx, ny);
			}
		}
		return cnt;
	}

}
