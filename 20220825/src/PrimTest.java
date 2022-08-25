import java.util.Scanner;

public class PrimTest {
	static int V, E;

	static class Node {
		int vertext, weight;
		Node next;

		public Node(int vertext, int weight, Node next) {
			super();
			this.vertext = vertext;
			this.weight = weight;
			this.next = next;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();

		Node[] adjList = new Node[V];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			// 무향 처리
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		} // 입력 완료
			// 정점수 만큼 자신을 신장트리에 포함시킬경우 최소비용을 관리하는 배열
		int[] minEdge = new int[V];
		boolean[] v = new boolean[V];
		// 신장트리에 현재 정점이 포함되어있는가를 판단할 수 있는 방문체크 배열

		// minEdge 배열에 최대값으로 초기화
		for (int i = 0; i < V; i++) {
			minEdge[i] = Integer.MAX_VALUE;
		}
		// 1.임의의 시작점 처리, 0을 임의의 정점으로 선정
		minEdge[0] = 0;
		int result = 0; // 최소신장트리 비용구하기

		int min = Integer.MAX_VALUE;
		int minVertex = -1; // 선택되지 않는 초기값 설정
		for (int i = 0; i < V; i++) { // 정점 V개 만큼 반복
			// step1 : 신장트리에 포함되지 않은 정점들에서 midEdge배열에서 가장 작은 값을 찾는다.
			// MST 프림의 핵심 소스1
			for (int j = 0; j < V; j++) {
				if (v[j]) { // 신장트리에 포함된 정점의 minEdge 배열은 무시
					continue;
				}
				if (min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			// step2 : 신장트리에 추가
			v[minVertex] = true;
			result += min;
			// step3 : 신장트리에 새롭게 추가된 정점들과 연결되 비용들로
//                minEdge 배열의 비용을 업데이트를 함
			for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
				if (v[temp.vertext]) { // 신장트리에 포함된 정점이면 무시
					continue;
				}
				// MST 프림의 핵심 소스2
				if (minEdge[temp.vertext] > temp.weight) {
					minEdge[temp.vertext] = temp.weight;
				}
			}
		}

		System.out.println(result);

	}

}