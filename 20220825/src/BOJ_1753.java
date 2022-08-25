import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
	static class Node {
		int to, weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	private static final int INF = 987654321;
	static int V, E, K;
	static List<Node>[] list;
	static int[] dist;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		// 간선 정보
		list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		// 리스트에 그래프 정보를 초기화
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, weight));
		}

		// 다른 정점까지의 비용
		dist = new int[V + 1];
		Arrays.fill(dist, INF);

		// 다익스트라 알고리즘
		dijkstra(K);
		
		// 출력
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF)
				sb.append("INF\n");
			else
				sb.append(dist[i]).append("\n");
		}
		System.out.print(sb);
	}

	private static void dijkstra(int start) {
		
		PriorityQueue<Node> queue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
		boolean[] check = new boolean[V + 1];
		// 시작점
		queue.add(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.to;
			// 방문했으면 패스
			if (check[cur] == true)
				continue;
			// 방문 체크
			check[cur] = true;
			// 현재 정점을 경유지로 거쳐 인접 정점들로 가는 비용 비교
			for (Node node : list[cur]) {
				if (dist[node.to] > dist[cur] + node.weight) {
					dist[node.to] = dist[cur] + node.weight;
					queue.add(new Node(node.to, dist[node.to]));
				}
			}
		}
	}
}