import java.util.Arrays;
import java.util.Scanner;

// nCm 의 모든 경우를 나열하라.
public class BOJ_15649 {

	static int[] nums;
	static boolean[] isSelected;
	static int n;
	static int m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		nums = new int[m];
		isSelected = new boolean[n + 1];

		perm(0);
	}

	private static void perm(int cnt) {
		if (cnt == m) {
			for(int num : nums) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (isSelected[i])
				continue;
			nums[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

}
