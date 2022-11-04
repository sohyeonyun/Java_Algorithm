import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 평범한 배낭
 */
public class BOJ_12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Item[] items = new Item[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			items[i] = new Item(w, v);
		}

		int[][] dp = new int[N + 1][K + 1];
		// i번째 아이템까지 고려해서 담음
		for (int i = 1; i <= N; i++) {
			int w = items[i].weight;
			int v = items[i].value;
			// 1~K까지 무게에 대해 고려
			for (int j = 1; j <= K; j++) {
				// 현재 아이템을 담을 수 있는 경우 && 안담을때보다 가치가 더 크면 담는다.
				if (j >= w && (dp[i - 1][j] <= dp[i - 1][j - w] + v)) {
					dp[i][j] = dp[i - 1][j - w] + v;
				} else { // 현재 아이템을 담을 수 없는 경우, 안담았을때의 가치로 업데이트
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		// 모든 경우를 고려했을 때가 최대 가치
		System.out.println(dp[N][K]);

	}

	static class Item {
		int weight, value;

		public Item(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
	}

}
