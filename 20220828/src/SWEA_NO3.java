import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_NO3 {

	static int N;
	static int[] cards;
	static int answer = -1;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			answer = -1;

			N = Integer.parseInt(br.readLine());
			cards = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}

			bfs();

			// 출력
			sb.append("#").append(t).append(" ").append(answer).append("\n");

		}
		System.out.print(sb);
	}

	private static void bfs() {
		// 셔플 횟수, 카드열
		Queue<Data> q = new LinkedList<>();
		q.add(new Data(0, cards));

		Data d;
		while (!q.isEmpty()) {
			d = q.poll();

			// 정렬되었으면 종료
			if (isSorted(d.cards)) {
				answer = d.cnt;
				return;
			}

			// 이미 5번 셔플했으면 패스
			if (d.cnt >= 5)
				continue;

			// 다이얼 0 ~ N-1 조정하며 셔플한다.
			for (int i = 0; i < N; i++) {
				// 셔플 카운트 늘리며 셔플 한 결과 넘겨준다.
				q.add(new Data(d.cnt + 1, shuffle(i, d.cards)));
			}
		}

	}

	private static boolean isSorted(int[] cards) {
		boolean des = (cards[0] > cards[1]) ? true : false;

		for (int i = 0; i < cards.length - 1; i++) {
			if (des) {
				if (!(cards[i] > cards[i + 1]))
					return false;
			} else {
				if (!(cards[i] < cards[i + 1]))
					return false;
			}
		}

		return true;
	}

	private static int[] shuffle(int x, int[] cards) {

		int left = 0;
		int right = N / 2;
		int[] nums = new int[N];
		int cnt = 0;

		// 셔플 규칙 - N/2 위아래로 대칭이다.
		if (x < N / 2) {
			for (int i = 0; i < N / 2 - x; i++) {
				nums[cnt++] = cards[left++];
			}
			while (cnt < N) {
				if (right < N) {
					nums[cnt++] = cards[right++];
				}
				if (left < N / 2) {
					nums[cnt++] = cards[left++];
				}
			}
		} else {
			for (int i = 0; i < x - N / 2 + 1; i++) {
				nums[cnt++] = cards[right++];
			}
			while (cnt < N) {
				if (left < N / 2) {
					nums[cnt++] = cards[left++];
				}
				if (right < N) {
					nums[cnt++] = cards[right++];
				}
			}
		}

		return nums;
	}

	static class Data {
		int cnt;
		int[] cards;

		public Data(int cnt, int[] cards) {
			super();
			this.cnt = cnt;
			this.cards = cards;
		}
	}
}
