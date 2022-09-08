import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 트리의 지름
 */

//총 dfs를 두 번 시행하면 된다.
// step 1) 아무 정점 기준으로 dfs를 한다.
// 한 번 dfs를 하면, 지름의 양 끝점 중 하나를 찾을 수 있다.
// 왜? 어떤 정점을 기준으로 하더라도 거리가 최대일 때가 지름의 양 끝점 중 하나다.
// step 2) 찾은 끝점 기준으로  dfs를 한다.
// 이 때의 거리가 가장 길 때가 트리의 지름이다.
/*
 * 노드를 구슬, 간선을 실이라고 생각한다면, 
 * 구슬 하나를 잡았을 때 축 늘어지는 나머지 구슬 중 가장 실이 길게 늘어나는게 지름의 끝점
 * 그 끝 구슬을 다시 잡고 늘어뜨리면? 가장 긴 실이 무조건 최장 길이가 된다.
 */
public class BOJ_1167 {

	static int V;
	static ArrayList<Node>[] list;
	static boolean[] v;
	static long MAX = 0;
	static int endNode = 0;

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

		// 1번 기준으로 시작
		v = new boolean[V + 1];
		v[1] = true;
		dfs(1, 0);
		// 찾은 끝점을 기준으로 시작
		v = new boolean[V + 1];
		v[endNode] = true;
		dfs(endNode, 0);
		
		System.out.println(MAX);

	}

	static void dfs(int idx, long sum) {

		for (Node node : list[idx]) {
			int cur = node.to;
			if (v[cur])
				continue;
			v[cur] = true;
			dfs(cur, sum + node.w);
		}
		
		if(MAX < sum) {
			MAX = sum;
			endNode = idx;
		}
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
