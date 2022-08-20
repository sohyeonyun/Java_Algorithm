import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 촌수계산
 */
public class BOJ_2644 {
	
	static class Node {
		int idx;
		int cnt;
		public Node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}
	
	static int N, A, B, M;
	static ArrayList<Integer> [] list;
	static boolean[] v;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = sc.nextInt();
		B = sc.nextInt();
		M = sc.nextInt();
		
		// 인접 리스트 초기화
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 인접 간선 입력 받기 (양방향)
		for(int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			list[from].add(to);
			list[to].add(from);
		}
		
		// 방문 배열 초기화
		v = new boolean[N + 1];
		
		// 탐색 결과 출력
		System.out.println(bfs(A));		
		
	}

	static int bfs(int start) {
		
		// 큐에 현재 인덱스와 촌수를 함께 저장하기 위해 노드 클래스를 사용
		Queue<Node> q = new LinkedList<Node>();
		// 시작점 삽입
		q.offer(new Node(start, 0));
		v[start] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cur = node.idx;
			int cnt = node.cnt;
			
			// 일치하는 사람을 찾으면 촌수 리턴
			if(cur == B) return cnt;
			
			// 인접 정점을 방문하지 않았으면 큐에 삽입
			for(Integer idx: list[cur]) {
				if(!v[idx]) {
					// 촌수 늘려서 삽입
					q.offer(new Node(idx, cnt + 1));
					v[idx] = true;
				}
			}
		}
		
		// 일치하는 사람이 없으면 -1
		return -1;
	}

}
