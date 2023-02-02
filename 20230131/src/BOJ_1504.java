import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504 {
    static int N, E; // 정점, 간선 수
    static ArrayList<ArrayList<Node>> list; // 인접리스트
    static int[] dist; // 최단거리
    static boolean[] visited; // 방문 체크
    static final int INF = 200_000_000;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
 
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
        	list.add(new ArrayList<>());
        }
 
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        
        // 양방향 인접 리스트 구현.
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
 
            // 양방향
            list.get(start).add(new Node(end, cost));
            list.get(end).add(new Node(start, cost));
        }
 
        // 반드시 거쳐야 하는 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
 
        // 1 -> v1 -> v2 -> N
        int res1 = dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, N);
 
        // 1 -> v2 -> v1 -> N으로 가는 경우
        int res2 = dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, N);
 
        int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);
        System.out.println(ans);
    }
 
    // 다익스트라
    public static int dijkstra(int start, int end) {
 
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        
        pq.offer(new Node(start, 0));
        dist[start] = 0;
 
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;
 
            if (!visited[cur]) {
            	visited[cur] = true;
 
                for (Node node : list.get(cur)) {
                	if (visited[node.end]) {
                		continue;
                	}
                    if (dist[node.end] > dist[cur] + node.cost) {
                        dist[node.end] = dist[cur] + node.cost;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }
 
        return dist[end];
    }
    
    static class Node implements Comparable<Node> {
        int end;
        int cost;
     
        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
     
        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}
