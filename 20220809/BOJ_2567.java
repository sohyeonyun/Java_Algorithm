import java.util.Scanner;

public class BOJ_2567 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] map = new int[100][100];
		// 색종이를 칠한다. (1로 표시)
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					map[j][k] = 1;
				}
			}
		}

		// 상하좌우
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		int cnt = 0;
		// 0과 1이 붙어있으면 둘레이다.
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				// 1인 칸에서 
				if (map[i][j] == 1) {
					// 상하좌우가 0인지 확인 
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						// 도화지 내부 
						if (0 <= nx && nx < 100 && 0 <= ny && ny < 100 && map[nx][ny] == 0) cnt++;
						// 도화지 벽면에 붙어 있는 경우
						if (nx < 0) cnt++;
						if (nx >= 100) cnt++;
						if (ny < 0) cnt++;
						if (ny >= 100) cnt++;
					}
				}
			}
		}

		System.out.println(cnt);

	}

}