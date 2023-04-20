import java.util.*;
import java.io.*;

public class g4_10282 {
	
	static int N, dep, comp;
	static ArrayList<Node>[] list;
	static boolean[] v;
	static int[] dist;
	static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dep = Integer.parseInt(st.nextToken());
			comp = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[N + 1];
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<dep; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				list[b].add(new Node(a, s));
			}
			
			count = 0;
			dist = new int[N + 1];
			v = new boolean[N + 1];
			
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			dijkstra();
			
			int time = 0;
			for(int i=1; i<=N; i++) {
				if(dist[i] != Integer.MAX_VALUE) {
					time = Math.max(time, dist[i]);
				}
			}
			
			System.out.println(count + " " + time);
		}
		
	}
	
	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(comp, 0));
		dist[comp] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(v[cur.n]) {
				continue;
			}
			count++;
			v[cur.n] = true;
			
			for(Node node: list[cur.n]) {
				if(dist[node.n] > dist[cur.n] + node.s) {
					dist[node.n] = dist[cur.n] + node.s;
					pq.offer(new Node(node.n, dist[node.n]));
				}
			}
		}
	}
	
	public static class Node implements Comparable<Node>{
		int n, s;
		
		public Node(int n, int s) {
			this.n = n;
			this.s = s;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.s - o.s;
		}
	}

}
