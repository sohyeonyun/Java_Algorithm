import java.util.Scanner;

public class _hw_SWEA_1954 {
	static int idx = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int n = sc.nextInt();
			int[][] map = new int[n][n];
			
			idx = 1;			
			recursive(0, n, map);
			
			System.out.println("#" + t);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.printf("%d ", map[i][j]);
				}
				System.out.println();
			}
		}
	}

	static void recursive(int a, int b, int[][] map) {
		if (a > b) {
			return;
		}
		for (int i = a; i < b; i++) {
			map[a][i] = idx++;
		}
		for (int i = a + 1; i < b; i++) {
			map[i][b - 1] = idx++;
		}
		for (int i = b - 2; i >= a; i--) {
			map[b - 1][i] = idx++;
		}
		for (int i = b - 2; i > a; i--) {
			map[i][a] = idx++;
		}
		recursive(a + 1, b - 1, map);
	}

}
