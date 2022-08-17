import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// SWEA 2805 - 농작물 수확하기
public class _5_SWEA2805 {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = Integer.parseInt(sc.nextLine());
		for (int t = 1; t <= T; t++) {
			// 입력 받기
			int n = Integer.parseInt(sc.nextLine());
			int[][] map = new int[n][n];

			for (int i = 0; i < n; i++) {
				char[] s = sc.nextLine().toCharArray();
				for (int j = 0; j < n; j++) {
					map[i][j] = s[j] - '0';
				}
			}

			// 마름모 탐색
			int sum = 0;
			for (int i = 0; i < n / 2 + 1; i++) {
				for (int j = n / 2 - i; j < n / 2 + i + 1; j++) {
					sum += map[i][j];
				}
			}
			for (int i = 1; i < n / 2 + 1; i++) {
				for (int j = i; j < n - i; j++) {
					sum += map[n / 2 + i][j];
				}
			}

			System.out.printf("#%d %d%n", t, sum);

		}
	}

}
