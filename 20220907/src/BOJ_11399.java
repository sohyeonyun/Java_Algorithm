import java.util.Arrays;
import java.util.Scanner;

/*
 * ATM - 정렬, 그리
 */

public class BOJ_11399 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		int ans = 0;
//		1
//		1 2
//		1 2 3
//		1 2 3 3
//		1 2 3 3 4
		for (int i = 0; i < N; i++) {
			ans += arr[i] * (N - i);
		}

		System.out.println(ans);

	}

}
