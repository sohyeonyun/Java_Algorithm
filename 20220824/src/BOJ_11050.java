import java.util.Scanner;

/*
 * 이항계수 1
 */

public class BOJ_11050 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		int sum = 1;
		for (int i = 0; i < k; i++) {
			sum *= (n - i);
		}
		for (int i = 0; i < k; i++) {
			sum /= (k - i);
		}
		System.out.println(sum);
	}

}
