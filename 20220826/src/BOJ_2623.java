import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 음악 프로그램
 */
public class BOJ_2623 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 가수 수
		int M = Integer.parseInt(st.nextToken()); // PD 수

		// 위상 정렬
		int[] inDegree = new int[N + 1];
		// 간선 정보 저장할 리스트
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		// 간선 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to;
			for (int j = 0; j < k - 1; j++) {
				to = Integer.parseInt(st.nextToken());
				list[from].add(to);
				from = to;
				inDegree[to]++;
			}
		}

		// 위상정렬 위해 큐 사용한다.
		Queue<Integer> q = new LinkedList<>();
		// 차수가 0인 정점을 모두 큐에 넣는다.
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		int cnt = 0; // 방문한 가수 수
		while (!q.isEmpty()) {
			int cur = q.poll();
			cnt++;
			sb.append(cur).append("\n");

			// 인접한 정점 차수 빼주기
			for (int i : list[cur]) {
				inDegree[i]--;
				if (inDegree[i] == 0) {
					q.offer(i);
				}
			}
		}

		System.out.println((cnt == N) ? sb : 0);

	}

}
