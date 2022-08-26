import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 줄세우기 - 위상정렬
 */
public class BOJ_2252 {

	static ArrayList<Integer> result = new ArrayList<>();
	static int N; // 학생수
	static int M; // 관계수
	static ArrayList<Integer>[] list = null;
	// 위상정렬 -> inDegree 배열, 큐 자료구조
	static int[] inDegree = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		// step1. 진입차수 관리배열 생성
		inDegree = new int[N + 1];

		// step2. 입력받으면서 집입 차수 관리 배열에 1씩 증가
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			list[x].add(y);
			inDegree[y]++;
		}

		// step3. 진입차수가 0인 객체를 모두 큐에 삽입한다.
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		// 가지치기 - 큐 사이즈가 0이면 종료
		if (q.size() == 0) {
			return;
		}

		// step4. 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			// step4-1. 큐에서 하나 빼서 작업을 진행한다.
			int cur = q.poll();
			result.add(cur);
			// step4-2. 나랑 연결되어 있는 모든 정점들의 진입차수 배열에서 1씩 감소한다.
			for (int num : list[cur]) {
				inDegree[num]--;
				// step4-3. 현재 정점의 진입차수가 0인 정점은 큐에 삽입한다.
				if (inDegree[num] == 0) {
					q.offer(num);
				}
			}
		}
		
		// result.size() 값이 N이 아니면 사이클 존재
		if(result.size() != N) {
			return;
		}

		// 출력
		for (Integer num : result) {
			System.out.print(num + " ");
		}
		System.out.println();

	}

}
