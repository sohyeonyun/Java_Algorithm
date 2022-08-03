import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// SWEA 1210 - Ladder1
public class _4_SWEA1210 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		for (int T = 1; T <= 10; T++) {

			// 100 * 100 맵
			int n = 100;
			int[][] map = new int[n][n];

			// 도착 지점(2) 의 위치
			int x = 0;
			int y = 0;

			// 입력받기
			sc.nextInt();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 2) {
						x = i;
						y = j;
					}
				}
			}

			// 끝 -> 시작점 으로 사다리 타기
			while (x != 0) { // 맨 윗줄까지 올라오면 종료
				x--;

				// 왼쪽에 사다리 있으면
				if (y - 1 >= 0 && map[x][y - 1] == 1) {
					while (y - 1 >= 0 && map[x][y - 1] != 0) {
						y--;
					}
				}
				// 오른쪽에 사다리 있으면
				else if (y + 1 < n && map[x][y + 1] == 1) {
					while (y + 1 < n && map[x][y + 1] != 0) {
						y++;
					}
				}

			}

			System.out.printf("#%d %d%n", T, y);

		}
	}

}
