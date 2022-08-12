import java.util.Arrays;
import java.util.Scanner;

/*
 * 0 ~ 9 사이의 숫자 카드에서 임의의 카드 6장을 뽑았을 때,
 * 3장의 카드가 연속적인 번호를 갖는 경우를 run 이라 하고,
 * 3장의 카드가 동일한 번호를 갖는 경우를 triplet 이라고 한다.
 * 6장의 카드가 run과 triplet 으로만 구성된 경우를 baby-gin 으로 부른다.
 */

// 순열로 풀이
public class BabyGin {

	static int n = 6;
	static boolean[] isSelected = new boolean[n];
	static int[] nums = new int[n];
	static int[] input = new int[n];
	static boolean answer = false;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		for (int i = 0; i < n; i++) {
			input[i] = s.charAt(i) - '0';
		}

		perm(0);

		System.out.println(answer);
	}

	static void perm(int cnt) {
		if (cnt == n) {
			if (isBabyGin()) answer = true;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (isSelected[i])
				continue;
			nums[cnt] = input[i];
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	static boolean isBabyGin() {
		if (isRun(0) && isRun(3))
			return true;
		if (isRun(0) && isTriplet(3))
			return true;
		if (isTriplet(0) && isRun(3))
			return true;
		if (isTriplet(0) && isTriplet(3))
			return true;

		return false;
	}

	static boolean isRun(int idx) {
		if (nums[idx] + 1 == nums[idx + 1] && nums[idx + 1] + 1 == nums[idx + 2]) {
			return true;
		}
		return false;
	}

	static boolean isTriplet(int idx) {
		if (nums[idx] == nums[idx + 1] && nums[idx + 1] == nums[idx + 2]) {
			return true;
		}
		return false;
	}

}
