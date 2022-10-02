import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA_1952 수영장
 */

public class SWEA_1952 {

	static int[] prices, uses;
	static int MIN;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			prices = new int[4]; // 1일, 1달, 3달, 1년
			for (int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			uses = new int[12];
			for (int i = 0; i < 12; i++) {
				uses[i] = Integer.parseInt(st.nextToken());
			}

			MIN = prices[3]; // 1년 이용권

			find(0, 0, 0);
			find(0, 1, 0);
			find(0, 2, 0);

			sb.append("#").append(t).append(" ").append(MIN).append("\n");
		}
		System.out.print(sb);

	}

	private static void find(int month, int priceIdx, int sum) {

		if (month >= 12) {
			MIN = Math.min(MIN, sum);
			return;
		}

		int price = 0;
		int nextMonth = month;
		if (priceIdx == 0) {
			price = prices[priceIdx] * uses[month];
			nextMonth += 1;
		} else if (priceIdx == 1) {
			price = prices[priceIdx];
			nextMonth += 1;
		} else if (priceIdx == 2) {
			price = prices[priceIdx];
			nextMonth += 3;
		}

		find(nextMonth, 0, sum + price);
		find(nextMonth, 1, sum + price);
		find(nextMonth, 2, sum + price);

	}

}
