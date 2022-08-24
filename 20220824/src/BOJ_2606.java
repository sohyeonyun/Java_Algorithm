import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 바이러스
 */

public class BOJ_2606 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		// 간선 저장
		ArrayList<Integer>[] list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		// 간선 입력받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}

		// 1번 부터
		Queue<Integer> q = new LinkedList<>();
		boolean[] v = new boolean[n + 1];
		v[1] = true;
		q.add(1);

		// bfs
		int ans = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (Integer next : list[cur]) {
				if (v[next])
					continue;
				v[next] = true;
				q.add(next);
				ans++;
			}
		}
		System.out.println(ans);
	}

}
