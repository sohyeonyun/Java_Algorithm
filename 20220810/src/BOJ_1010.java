import java.util.Scanner;

// mCn
public class BOJ_1010 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			n = Math.min(n, m - n);

			long result = 1;

			for (int i = m; i >= m - n + 1; i--) {
				result *= i;
			}
			for (int i = n; i >= 1; i--) {
				result /= i;
			}

			System.out.println(result);
		}
	}

}
