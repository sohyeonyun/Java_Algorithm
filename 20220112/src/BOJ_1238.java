import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 파
 */
public class BOJ_1238 {
	
	static int N, M, X;
	static List<Node>[] edges;
	static int[] dist;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 단방향 도로 개수
		X = Integer.parseInt(st.nextToken()); // 파티할 마을 번호
		
		// 도로 저장
		edges = new ArrayList[N + 1];
		for(int i=1; i<=N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		// 도로 정보 입력 받기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[start].add(new Node(end, cost));
		}
		
		// 거리 정보
		dist = new int[N + 1];
		
		// 학생 마을 -> X번 마을 -> 학생 마을
		int MAX = 0;
		for(int i=1; i<=N; i++) {
			int sum = 0;
			// 거리 초기화
			Arrays.fill(dist, 987654321);
			
			// 다익스트라 1
			dijkstra(i);
			sum = dist[X];
			
			// 거리 초기화
			Arrays.fill(dist, 987654321);
			// 다익스트라 2
			dijkstra(X);
			sum += dist[i];
			
			MAX = Math.max(MAX, sum);
		}
		
		System.out.println(MAX);
		
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		boolean[] v = new boolean[N + 1];
//		v[start] = true;
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 방문 체크
			if(v[cur.end]) {
				continue;
			}
			v[cur.end] = true;
			
			for(Node node: edges[cur.end]) {
				// 지금까지 기록한 비용 vs 현재 노드 거쳐서 가는 비용
				if(dist[node.end] > dist[cur.end] + node.cost) {
					dist[node.end] = dist[cur.end] + node.cost;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
			
		}
		
	}

	static class Node implements Comparable<Node>{
		int end, cost;

		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}
	}

}
