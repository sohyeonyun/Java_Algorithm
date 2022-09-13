import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * 최대 힙
 */
public class BOJ_11279 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int k = Integer.parseInt(br.readLine());
			if (k == 0) {
				sb.append(pq.isEmpty() ? 0 : pq.poll()).append('\n');
			} else {
				pq.add(k);
			}
		}

		System.out.print(sb);
	}

}
