import java.util.Scanner;

public class BOJ_11659 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int[] sum = new int[n + 1]; // 누적합
		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + sc.nextInt();
		}

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println(sum[b] - sum[a - 1]);
		}

	}

}
