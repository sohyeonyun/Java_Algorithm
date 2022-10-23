import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 종이의 개수
 */
public class BOJ_1780 {

	static int N;
	static int[][] map;
	static int[] answer = new int[3];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, N);

		System.out.println(answer[0]);
		System.out.println(answer[1]);
		System.out.println(answer[2]);

	}

	private static void dfs(int r, int c, int size) {
		// 길이 1이면 종료
		if (size == 1) {
			update(r, c, size);
			return;
		}

		// 모두 같은 수라면 개수 업데이트
		if (isSame(r, c, size)) {
			update(r, c, size);
			return;
		}
		// 같은 수 아니라면, 재귀
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				dfs(r + size / 3 * i, c + size / 3 * j, size / 3);
			}
		}

	}

	private static void update(int r, int c, int size) {
		if (map[r][c] == -1) {
			answer[0]++;
		} else if (map[r][c] == 0) {
			answer[1]++;
		} else {
			answer[2]++;
		}
	}

	// 잘린 색종이가 같은 수로 이뤄져 있는지
	private static boolean isSame(int r, int c, int size) {
		int num = map[r][c];
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (num != map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}
