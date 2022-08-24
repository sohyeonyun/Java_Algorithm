import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 게리멘더링
 */

public class BOJ_17471 {

	static int n;
	static int[] numList;
	static int[][] edges;
	static boolean[] isSelected;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		// 인구 수 입력받기
		st = new StringTokenizer(br.readLine());
		numList = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}

		// 간선 정보 입력 받기
		edges = new int[n + 1][];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			edges[i] = new int[m];
			for (int j = 0; j < m; j++) {
				edges[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 부분집합 저장할 배열
		isSelected = new boolean[n + 1];

		// 부분집합 구하기
		subset(1);

		// 결과 출력
		if (MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(MIN);
		}
	}

	private static void subset(int idx) {

		if (idx == n + 1) {
			// 두 구역으로 나뉘면 최솟값 업데이트
			if (isAbleSet()) {
				MIN = Math.min(MIN, getDiff());
			}
			return;
		}

		isSelected[idx] = true;
		subset(idx + 1);
		isSelected[idx] = false;
		subset(idx + 1);
	}

	private static boolean isAbleSet() {
		// true 지역 수, false 지역 수
		int[] cnt = new int[2];
		int[] start = new int[2];
		for (int i = 1; i <= n; i++) {
			if (isSelected[i]) {
				cnt[0]++;
				start[0] = i;
			} else {
				cnt[1]++;
				start[1] = i;
			}
		}
		
		// 한쪽으로 치우친 경우 false
		if(cnt[0] == 0 || cnt[1] == 0) {
			return false;
		}

		// true 구역과 false 구역을 각각 bfs
		for (int i = 0; i < 2; i++) {
			// bfs 위해 큐와 방문 배열 초기화
			Queue<Integer> q = new LinkedList<>();
			boolean[] v = new boolean[n + 1];
			boolean flag = (i == 0) ? true : false;
			// 시작점
			q.offer(start[i]);
			v[start[i]] = true;
			cnt[i]--;
			
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int next : edges[cur]) {
					// 다른 구역이거나 방문했으면 패스
					if (isSelected[next] != flag || v[next])
						continue;
					// 큐에 삽입
					v[next] = true;
					q.add(next);
					cnt[i]--;
				}
			}
		}
		
		if (cnt[0] == 0 && cnt[1] == 0)
			return true;

		return false;
	}

	private static int getDiff() {
		int sum1 = 0, sum2 = 0;
		for (int i = 1; i <= n; i++) {
			if (isSelected[i]) {
				sum1 += numList[i];
			} else {
				sum2 += numList[i];
			}
		}
		return Math.abs(sum1 - sum2);
	}

}
