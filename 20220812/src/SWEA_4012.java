import java.util.Arrays;
import java.util.Scanner;

/*
 * 요리사
 */
public class SWEA_4012 {

	static int n;
	static int[][] arr;
	static boolean[] total;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			MIN = Integer.MAX_VALUE;
			// 입력
			n = sc.nextInt();
			arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			// 조합 nC(n/2) (재료 n개 중 n/2 개 선택)
			total = new boolean[n];
			comb(0, 0);

			System.out.printf("#%d %d%n", t, MIN);
		}

	}

	static void comb(int cnt, int start) {
		if (cnt == n / 2) {
//			System.out.println(Arrays.toString(total));
			int sum1 = 0, sum2 = 0;
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					if (total[i] && total[j])
						sum1 += (arr[i][j] + arr[j][i]);
					if (!total[i] && !total[j])
						sum2 += (arr[i][j] + arr[j][i]);
				}
			}
			MIN = Math.min(MIN, Math.abs(sum1 - sum2));
			return;
		}

		for (int i = start; i < n; i++) {
			total[i] = true;
			comb(cnt + 1, i + 1);
			total[i] = false;
		}
	}

}
