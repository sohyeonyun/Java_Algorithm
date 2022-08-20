import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * DFS와 BFS
 */
public class BOJ_1260 {

	static ArrayList<Integer>[] list = null;
	static boolean[] v;
	static int N;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		int V = sc.nextInt();

		// 간선 정보 담을 리스트 초기화
		list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}

		// 간선 정보 입력 받음 (양방향)
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			list[from].add(to);
			list[to].add(from);
		}
		
		// 정점 번호가 작은 것을 먼저 방문하기 때문에 정렬
		for (int i = 1; i < N + 1; i++) {
			list[i].sort((a, b) -> a.compareTo(b));
		}

		// 방문 배열 초기화
		v = new boolean[N + 1];
		dfs(V);
		
		System.out.println();

		// 방문 배열 초기화
		v = new boolean[N + 1];
		bfs(V);

	}

	static void dfs(int cur) {

		// 방문 표시 후 출력
		v[cur] = true;
		System.out.print(cur + " ");

		// 인접 정점 하나씩 꺼내서 방문하지 않았으면 재귀 탐색
		for (Integer idx : list[cur]) {
			if (v[idx]) continue;

			dfs(idx);
		}

	}

	static void bfs(int start) {

		// bfs를 위한 큐 생성
		Queue<Integer> queue = new LinkedList<Integer>();
		// 큐에 시작 정점 삽입 후 방문 표시
		queue.offer(start);
		v[start] = true;
		
		while(!queue.isEmpty()) {
			// 큐에서 정점 꺼내고 출력
			int cur = queue.poll();
			System.out.print(cur + " ");
			
			// 인접 정점 하나씩 꺼내서 방문하지 않았으면 큐에 다시 삽입하고 방문 표시
			for(Integer idx: list[cur]) {
				if(v[idx]) continue;
				queue.offer(idx);
				v[idx] = true;
			}
		}

	}

}



