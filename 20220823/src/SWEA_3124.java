import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3124 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}

	static int[] p;
	static Edge[] edges;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 간선 정보 입력받기
			edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(a, b, c);
			}
			
			// 가중치로 간선 정렬
			Arrays.sort(edges);

			// 각 원소마다 집합
			makeSet(V);
			
			long sum = 0;
			int cnt = 0;
			for(Edge edge: edges) {
				if(unionSet(edge.from, edge.to)) {
					sum += edge.cost;
					cnt++;
				}
				if(V - 1 == cnt) {
					break;
				}
			}
			
			sb.append("#").append(t).append(" ").append(sum).append('\n');
			
		}
		System.out.print(sb);
	}

	static void makeSet(int V) {
		p = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(x == p[x]) return x;
		return p[x] = findSet(p[x]);
	}
	
	static boolean unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if(x == y) return false;
		
		p[y] = x;
		return true;
	}

}
