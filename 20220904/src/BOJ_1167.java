import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 트리의 지름
 */
public class BOJ_1167 {

	static int V;
	static ArrayList<Node>[] list;
	static boolean[] v;
	static long MAX = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());

		// 간선 리스트 초기화
		list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		// 간선 입력받기
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to, weight;
			while (true) {
				to = Integer.parseInt(st.nextToken());
				if (to == -1) {
					break;
				}
				weight = Integer.parseInt(st.nextToken());
				list[from].add(new Node(to, weight));
			}
		}

		// 한 정점씩 dfs로 거리 계산
		for (int i = 1; i <= V; i++) {
			// 방문 체크 배열
			v = new boolean[V + 1];
			v[i] = true;
			// 
			dfs(i, 0);
		}
		
		System.out.println(MAX);

	}

	static void dfs(int idx, long sum) {

		for(Node node: list[idx]) {
			int cur = node.to;
			if(v[cur]) continue;
			v[cur] = true;
			dfs(cur, sum + node.w);
		}
		
		MAX = Math.max(MAX, sum);
	}

	static class Node {
		int to, w;

		public Node(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
	}

}
