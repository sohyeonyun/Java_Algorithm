import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238 {

	static ArrayList<Integer>[] list;
	static int[] v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			// 배열 안에 리스트 만들기
			list = new ArrayList[101];
			for (int i = 0; i < 101; i++) {
				list[i] = new ArrayList<Integer>();
			}
			v = new int[101];

			// 간선 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list[from].add(to);
			}

			// bfs
			System.out.printf("#%d %d%n", t, bfs(start));
		}
	}

	private static int bfs(int start) {

		// 마지막 연락이 몇 초 뒤인지 저장
		int lastCnt = 0;

		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		v[start] = 1;

		while (!q.isEmpty()) {
			int cur = q.poll();
			// 인접 노드 방문
			for (Integer idx : list[cur]) {
				// 이미 방문했으면 패스
				if (v[idx] != 0) continue;
				// 방문 안했으면 큐에 삽입
				q.add(idx);
				// 몇 번째 방문인지 v 리스트에 저장
				v[idx] = v[cur] + 1;
				lastCnt = Math.max(lastCnt, v[idx]);
			}
		}

		int MAX = 0;
		for (int i = 1; i <= 100; i++) {
			// 마지막에 연락 받은 사람들
			if (lastCnt == v[i]) {
				MAX = Math.max(MAX, i);
			}
		}
		return MAX;
	}

}
