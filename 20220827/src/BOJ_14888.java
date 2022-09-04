import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 연산자 끼워넣기 - 백트래킹 
 */

public class BOJ_14888 {

	static int N;
	static int[] nums; // 입력받은 숫자들
	static int plus, minus, multi, div;
	static int MIN = Integer.MAX_VALUE, MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		plus = Integer.parseInt(st.nextToken());
		minus = Integer.parseInt(st.nextToken());
		multi = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());

		calculate(nums[0], 1, plus, minus, multi, div);

		System.out.println(MIN);
		System.out.println(MAX);
	}

	static void calculate(int res, int i, int plus, int minus, int multi, int div) {
		if (i == N) {
			MIN = Math.min(MIN, res);
			MAX = Math.max(MAX, res);
			return;
		}

		if (plus > 0) {
			calculate(res + nums[i], i + 1, plus - 1, minus, multi, div);
		}
		if (minus > 0) {
			calculate(res - nums[i], i + 1, plus, minus - 1, multi, div);
		}
		if (multi > 0) {
			calculate(res * nums[i], i + 1, plus, minus, multi - 1, div);
		}
		if (div > 0) {
			calculate(res / nums[i], i + 1, plus, minus, multi, div - 1);
		}

	}

}
