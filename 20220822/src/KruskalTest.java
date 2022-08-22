import java.util.Arrays;
import java.util.Scanner;

public class KruskalTest {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
//            return this.weight - o.weight;
		}
	}

	static int[] P;
	static int V, E;
	static Edge[] eList;

	static void makeSet() {
		P = new int[V]; // 정점의 부모를 관리하는 배열 생성
		for (int i = 0; i < V; i++) { // 모든 정점을 자기 자신을 가르키도록 함
			P[i] = i;
		}
	}

	static int findSet(int a) { // a의 대표값 찾기
		if (a == P[a]) {
			return a;
		}
		// 경로 압축 후 찾은 부모 위치값 반환
		return P[a] = findSet(P[a]);
	}

//    반환값 
//    union 성공(검색해서 사이클 존재하지 않음) : true,
//    union 실패 : false
	static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) {
			return false;
		}
		// 바뀌어도 상관없으나 사향트리가 발생하는 테케가 존재할 수 있음
		P[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		// 정점의 갯수 만큼 부모 배열 생성이필요함
		makeSet();
		
		eList = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			eList[i] = new Edge(from, to, weight);
		}
		
		// 그리디한 판단 , 시간복잡도 => O(ElogE)
		Arrays.sort(eList);
		
		int res = 0;
		int cnt = 0;
		for (Edge d : eList) {
			if (unionSet(d.from, d.to)) {
				res += d.weight;
				cnt++;
			}
			if (cnt == V - 1) { // 연결된 간선의 갯수가 정점의 -1 갯수이면 종료
				break;
			}
		}
		System.out.println(res);

	}

}