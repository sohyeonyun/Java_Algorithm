import java.util.Arrays;
import java.util.Scanner;

/*
 * 괄호 추가하기
 */

public class BOJ_16637 {

	static int N;
	static int[] nums;
	static char[] comm;
	static boolean[] v;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = Integer.parseInt(sc.nextLine());
		String input = sc.nextLine();

		nums = new int[N / 2 + 1];
		comm = new char[N / 2];
		v = new boolean[N / 2];

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				nums[i / 2] = input.charAt(i) - '0';
			} else {
				comm[i / 2] = input.charAt(i);
			}
		}

		dfs(0);

		System.out.println(MAX);
	}

	private static void dfs(int idx) {
		if (idx == N / 2) {
			int[] newNums = Arrays.copyOf(nums, nums.length);
			char[] newComm = Arrays.copyOf(comm, comm.length);
			for (int i = 0; i < N / 2; i++) {
				// 연속해서 괄호치면 리턴 (중첩괄호)
				if (i < N / 2 - 1 && v[i] && v[i + 1]) {
					return;
				}
				// 괄호 연산 먼저 해줌.
				if (v[i]) {
					if (newComm[i] == '+') {
						newNums[i] += newNums[i + 1];
					} else if (newComm[i] == '-') {
						newNums[i] -= newNums[i + 1];
					} else if (newComm[i] == '*') {
						newNums[i] *= newNums[i + 1];
					}
					// 사용된 숫자 체크
					newComm[i] = '0';
				}

			}

			int sum = newNums[0];
			for (int i = 0; i < N / 2; i++) {
				// 괄호로 이미 계산된 연산
				if (newComm[i] == 0) {
					continue;
				}
				// 연산
				if (newComm[i] == '+') {
					sum += newNums[i + 1];
				} else if (newComm[i] == '-') {
					sum -= newNums[i + 1];
				} else if (newComm[i] == '*') {
					sum *= newNums[i + 1];
				}
			}
			MAX = Math.max(MAX, sum);

			return;
		}

		v[idx] = true;
		dfs(idx + 1);
		v[idx] = false;
		dfs(idx + 1);
	}

}
