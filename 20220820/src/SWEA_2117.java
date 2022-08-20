import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SWEA_2117 {

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int result = 0;

			// 입력 받기
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// K의 범위: 1 <= K <= N + 1
			for (int k = 1; k <= N + 1; k++) {

				// 서비스 영역이 k 일 때 비용
				int cost = k * k + (k - 1) * (k - 1);

				// 맵 처음부터 탐색
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {

						int home = 0;
						int[][] v = new int[N][N];

						// 마름모 구하기
						for (int a = 0; a < k; a++) {
							int x1 = i - a;
							int x2 = i + a;
							for (int b = -(k - a) + 1; b < k - a; b++) {
								int y = j + b;

								if (x1 >= 0 && y >= 0 && x1 < N && y < N && map[x1][y] != 0 && v[x1][y] != 1) {
									home++;
									v[x1][y] = 1;
								}
								if (x2 >= 0 && y >= 0 && x2 < N && y < N && map[x2][y] != 0 && v[x2][y] != 1) {
									home++;
									v[x2][y] = 1;
								}
							}
						}

						if ((M * home - cost) >= 0 && home > result) {
							result = home;
						}

					}
				}

			}

			System.out.printf("#%d %d%n", t, result);
		}
	}
}
