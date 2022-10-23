import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 컵라면
 */

public class BOJ_1781 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Data[] data = new Data[N];
		int maxDay = 0;

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			data[i] = new Data(day, cnt);

			maxDay = Math.max(maxDay, day);
		}

		// 데드라인별 컵라면수들을 리스트로 저장
		ArrayList<Integer>[] list = new ArrayList[maxDay + 1];
		for (int i = 1; i <= maxDay; i++) {
			list[i] = new ArrayList<>();
		}
		for (Data d : data) {
			list[d.day].add(d.cnt);
		}

		// 최대 컵라면 수
		int ans = 0;
		// 컵라면수 큰 값을 우선순위
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = maxDay; i > 0; i--) {
			// 데드라인 리스트에 값이 있다면
			if (!list[i].isEmpty()) {
				// 데드라인 긴거는 이전날에 해도됨.
				pq.addAll(list[i]);
			}
			if (!pq.isEmpty()) {
				// 데드라인 남은거 중에 컵라면 젤 큰거
				ans += pq.poll();
			}
		}

		System.out.println(ans);

	}

	static class Data {
		int day, cnt;

		public Data(int day, int cnt) {
			super();
			this.day = day;
			this.cnt = cnt;
		}
	}

}
