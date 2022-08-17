import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _SWEA_1873 {
	static Scanner sc;

	static void sol() {
		int x = 0; // 전차 초기 위치
		int y = 0;
		int d = 0; // 전차가 바라보는 방향 (상하좌우 순)
		int[] dx = { -1, 1, 0, 0 }; // 전차 움직임 (상하좌우 순)
		int[] dy = { 0, 0, -1, 1 };

		int h = sc.nextInt(); // 맵 높이
		int w = sc.nextInt(); // 맵 너비
		sc.nextLine(); // 엔터키 처리

		// 맵 입력 받기
		char[][] map = new char[h][w];
		for (int i = 0; i < h; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < w; j++) {
				map[i][j] = s.charAt(j);
				// 전차 초기 위치 찾기
				if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
					x = i;
					y = j;
					if (map[i][j] == '^')
						d = 0;
					if (map[i][j] == 'v')
						d = 1;
					if (map[i][j] == '<')
						d = 2;
					if (map[i][j] == '>')
						d = 3;
				}
			}
		}

		// 사용자 동작 입력 받기
		int n = sc.nextInt(); // 사용자 입력 개수
		sc.nextLine();
		char[] actions = sc.nextLine().toCharArray(); // 사용자 동작 배열

		// 사용자가 입력하는 동작에 따라 처리
		for (char action : actions) {
			if (action == 'U') { // 위 동작일 때, 방향 바꿔준다.
				d = 0;
				map[x][y] = '^';
				if (x - 1 >= 0 && map[x - 1][y] == '.') { // 위 칸이 맵을 벗어나지 않고 평지면 이동한다.
					map[x][y] = '.';
					x--;
					map[x][y] = '^';
				}
			} else if (action == 'D') {
				d = 1;
				map[x][y] = 'v';
				if (x + 1 < h && map[x + 1][y] == '.') {
					map[x][y] = '.';
					x++;
					map[x][y] = 'v';
				}
			} else if (action == 'L') {
				d = 2;
				map[x][y] = '<';
				if (y - 1 >= 0 && map[x][y - 1] == '.') {
					map[x][y] = '.';
					y--;
					map[x][y] = '<';
				}
			} else if (action == 'R') {
				d = 3;
				map[x][y] = '>';
				if (y + 1 < w && map[x][y + 1] == '.') {
					map[x][y] = '.';
					y++;
					map[x][y] = '>';
				}
			} else if (action == 'S') { // 포탄을 쏜다.
				int nx = x; // 포탄의 위치
				int ny = y;
				while (true) {
					nx += dx[d];
					ny += dy[d];
					if (nx < 0 || nx >= h || ny < 0 || ny >= w) {	// 맵 벗어나면 중지
						break;
					}
					if (map[nx][ny] == '*') { // 포탄이 벽돌을 쏘면, 평지가 됨
						map[nx][ny] = '.';
						break;
					} else if (map[nx][ny] == '#') { // 포탄이 강철을 쏘면, 소멸
						break;
					}
				}
			}
		}

		// 맵 출력
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			System.out.printf("#%d ", t);
			sol();
		}
	}

}
