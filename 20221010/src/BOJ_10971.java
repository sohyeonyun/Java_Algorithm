import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 외판원 순회 2
 */
public class BOJ_10971 {

	static int N;
	static int[][] map;
	static boolean[] v;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		v = new boolean[N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 모든 도시에서 시작해본다.
		for (int city = 0; city < N; city++) {
			dfs(city, city, 0, 0);
		}

		System.out.println(MIN);

	}

	private static void dfs(int start, int pre, int cnt, int sum) {

		// 모든 도시 들려서 출발지로 돌아왔으면
		if (cnt == N && start == pre) {
			MIN = Math.min(MIN, sum);
			return;
		}

		// 비용 가지치기
		if (sum >= MIN) {
			return;
		}

		// 다음으로 이동할 곳을 차례로 탐색
		for (int i = 0; i < N; i++) {
			// 연결 안되있으면 패스 (자기자신도)
			if (map[pre][i] == 0) {
				continue;
			}
			// 방문안했으면 이동
			if (!v[i]) {
				v[i] = true;
				dfs(start, i, cnt + 1, sum + map[pre][i]);
				v[i] = false;
			}
		}
	}

}
