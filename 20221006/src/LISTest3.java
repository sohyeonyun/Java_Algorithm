import java.util.Arrays;
import java.util.Stack;

public class LISTest3 {
	public static void main(String[] args) {
		int[] arr = { 3, 2, 6, 4, 5, 1 };
		doLst(arr);
	}

	static void doLst(int[] arr) {
		Pair[] tracking = new Pair[arr.length];
		int[] dp = new int[arr.length];
		dp[0] = arr[0];
		tracking[0] = new Pair(0, arr[0]);
		int idx = 0;
		for (int i = 1; i < arr.length; i++) {
			if (dp[idx] < arr[i]) {
				dp[++idx] = arr[i];

				tracking[i] = new Pair(idx, arr[i]);
			} else {
				int ii = lower_bound(dp, idx, arr[i]);
				dp[ii] = arr[i];

				tracking[i] = new Pair(ii, arr[i]);
			}
		}
		System.out.println(Arrays.toString(tracking));
		Stack<Integer> stack = new Stack<Integer>();
		int temp = idx;
		System.out.println("temp : " + temp);
		for (int i = arr.length - 1; i >= 0; i--) {
			if (temp == tracking[i].idx) {
				stack.push(tracking[i].value);
				--temp;
			}
		}
		System.out.println(stack.size());
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	static class Pair {
		int idx;
		int value;

		Pair(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Pair [idx=" + idx + ", value=" + value + "]";
		}

	}

	static int lower_bound(int[] dp, int end, int n) {
		int start = 0;
		while (start < end) {
			int mid = (start + end) / 2;
			if (dp[mid] >= n) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}
}