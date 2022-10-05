import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 0/1 Knapsack
 */
public class SWEA_3282 {

	static int N, K;
	static Item[] items;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			// 물건 개수, 가방 부피
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// 물건 정보 (Item: v, w)
			items = new Item[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				Item item = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				items[i] = item;
			}

			int ans = knapsack();

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
	}

	private static int knapsack() {

		int[] pre = new int[K + 1];
		int[] cur = new int[K + 1];

		for (int i = 1; i <= N; i++) {
			pre = cur;
			cur = new int[K + 1];
			
			for (int w = 1; w <= K; w++) {
				if (items[i].w > w) {
					cur[w] = pre[w];
				} else {
					cur[w] = Math.max(items[i].v + pre[w - items[i].w], pre[w]);
				}
			}
		}

		return cur[K];
	}

	static class Item {
		int v, w;

		public Item(int w, int v) {
			super();
			this.v = v;
			this.w = w;
		}
	}

}
