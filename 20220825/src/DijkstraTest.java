import java.util.Scanner;

public class DijkstraTest {
	static int V;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();

		int[][] adjMatrix = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = sc.nextInt();
			}
		} // 인접행렬 읽기 완료

		// 다익스트라 알고리즘 자료구소
		int start = 0; // 출발지 설정
		int end = V - 1;
		int[] D = new int[V]; // 출발지에서 자신으로 오는데 필요한 최소 비용 관리 테이블
		boolean[] v = new boolean[V]; // 경유지로 처리된지 여부 판단 배열

		// D 배열 초기화 처리
		for (int i = 0; i < V; i++) {
			D[i] = Integer.MAX_VALUE;
		}
		// 출발정점 처리
		D[start] = 0; // 출발지가 가장 먼저 선택되게 처림(자기에서 자기까지 비용은 0)
		int min;
		int minVertex;
		for (int i = 0; i < V; i++) {
			// step1. 미방문 정점 중에서 출발지에서 자신까지온 비용이 최소인 정점 선택
			// (방문해야 하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			min = Integer.MAX_VALUE;
			minVertex = -1;
			for (int j = 0; j < V; j++) {
				if (v[j]) {
					continue;
				}
				if (min > D[j]) {
					min = D[j];
					minVertex = j;
				}
			}
			// step2. 방문처리
			v[minVertex] = true;

			// step3. 선택된 정점을 경유지로 미방문한 정점들 중에 갈수 있는 정점들을
			// 경유해서 가능 비용을 더해서 미방문한 정점들의 비용이 유리하면 업데이트

			for (int j = 0; j < V; j++) {
				if (v[j]) { // 방문점점 무시
					continue;
				}
				if (adjMatrix[minVertex][j] == 0) { // 연결안됨 정점 무시
					continue;
				}
				if (D[j] > D[minVertex] + adjMatrix[minVertex][j]) { // 새로운 비용이 유리하면 갱신하기
					D[j] = D[minVertex] + adjMatrix[minVertex][j];
				}
			}
		}
		System.out.println(D[end]);
	}

}