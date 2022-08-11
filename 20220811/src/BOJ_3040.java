import java.util.Arrays;
import java.util.Scanner;

// 9C7
public class BOJ_3040 {
	static int[] input = new int[9];
	static int[] nums = new int[7];
	static int[] answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			input[i] = sc.nextInt();
		}

		comb(0, 0);

		for(int a : answer) {
			System.out.println(a);
		}
	}

	static void comb(int cnt, int start) {
		if (cnt == 7) {
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += nums[i];
			}
			if (sum == 100) {
				answer = Arrays.copyOf(nums, nums.length);
			}
			return;
		}

		for (int i = start; i < 9; i++) {
			nums[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}

}
