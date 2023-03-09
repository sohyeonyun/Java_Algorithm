import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _11779 {
	
	static int N, M, start, end;
	static ArrayList<Data>[] list;
	static int[] distance, route;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[s].add(new Data(e, c));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		// 초기화
		distance = new int[N + 1];
		route = new int[N + 1];
		visited = new boolean[N + 1];
		
		Arrays.fill(distance, 987654321);
		
		dijkstra();
		
		// 경로 구하기
		ArrayList<Integer> routes = new ArrayList<>();
		int cur = end;
		while(cur != 0) {
			routes.add(cur);
			cur = route[cur];
		}
		
		// 출력
		System.out.println(distance[end]);
		System.out.println(routes.size());
		for(int i=routes.size() - 1; i >= 0; i--) {
			System.out.print(routes.get(i) + " ");
		}
		
	}
	
	private static void dijkstra() {

		PriorityQueue<Data> pq = new PriorityQueue<>();
		pq.offer(new Data(start, 0));
		distance[start] = 0;
		route[start] = 0;
		
		while(!pq.isEmpty()) {
			Data cur = pq.poll();
			
			if(visited[cur.end]) {
				continue;
			}
			visited[cur.end] = true;

			for(int i=0; i<list[cur.end].size(); i++) {
				Data next = list[cur.end].get(i);
				if(distance[next.end] > distance[cur.end] + next.cost) {
					distance[next.end] = distance[cur.end] + next.cost;
					pq.offer(new Data(next.end, distance[next.end]));
					// 마지막엔 최소 비용으로 가는 경로가 저장되게 됨...
					route[next.end] = cur.end;
				}
			}
		}
		
	}

	static class Data implements Comparable<Data>{
		int end, cost;

		public Data(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Data o) {
			return this.cost - o.cost;
		}
	}

}
