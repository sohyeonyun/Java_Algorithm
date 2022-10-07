import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 맥주 마시면서 걸어가기
 */
public class BOJ_9205 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			// 입력
			int[][] input = new int[N + 2][2];
			for (int i = 0; i < N + 2; i++) {
				String[] s = br.readLine().split(" ");
				input[i][0] = Integer.parseInt(s[0]);
				input[i][1] = Integer.parseInt(s[1]);
			}

			// i -> j로 갈 수 있는지 여부
			boolean[][] map = new boolean[N + 2][N + 2];
			// 거리 1000 이하면 갈 수 있다.
			for (int i = 0; i < N + 2; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					if (Math.abs(input[i][0] - input[j][0]) + Math.abs(input[i][1] - input[j][1]) <= 1000) {
						map[i][j] = true;
						map[j][i] = true;
					}
				}
			}

			// 플로이드
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					for (int j = 0; j < N + 2; j++) {
						if (map[i][k] && map[k][j]) {
							map[i][j] = true;
						}
					}
				}
			}

			// 0(집) -> N+1(페스티벌) 까지 true면 갈 수 있다.
			System.out.println(map[0][N + 1] ? "happy" : "sad");

		}

	}

}
