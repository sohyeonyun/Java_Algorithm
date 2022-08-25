import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimTest_PQ2 {
	static int V, E;

	static class Data {
		int no, weight;

		public Data(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
	}

	static ArrayList<Data>[] list = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();

		list = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			list[i] = new ArrayList<Data>();
		}

		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			// 무향 처리
			list[from].add(new Data(to, weight));
			list[to].add(new Data(from, weight));
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
		PriorityQueue<Data> pq = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
		pq.offer(new Data(0, minEdge[0]));
		int cnt = 0;
		while (!pq.isEmpty()) { // 정점 V개가 선택된 만큼 반복
			// step1 : 신장트리에 포함되지 않은 정점들에서 midEdge배열에서 가장 작은 값을 찾는다.
			// MST 프림의 핵심 소스1=> PQ 적용
			// 우선 순위 큐에서 무조건 저렴한것 빼기

			Data minVertex = pq.poll();
			// 중복되어서 들어온 것은 배제한다.
			if (v[minVertex.no]) {
				continue;
			}
			// step2 : 신장트리에 추가
			v[minVertex.no] = true;
			result += minVertex.weight;
			cnt++;
			if (cnt == V) { // 모든 정점이 신장트리에 포함되어 있으면
				break;
			}

			// step3 : 신장트리에 새롭게 추가된 정점들과 연결되 비용들로
//                minEdge 배열의 비용을 업데이트를 함
			for (Data d : list[minVertex.no]) {
				if (v[d.no]) { // 신장트리에 포함된 정점이면 무시
					continue;
				}
				// MST 프림의 핵심 소스2
				// pq에 삽입한다.
				if (minEdge[d.no] > d.weight) {
					minEdge[d.no] = d.weight;
					pq.offer(new Data(d.no, d.weight));
				}
			}
		}

		System.out.println(result);

	}

}