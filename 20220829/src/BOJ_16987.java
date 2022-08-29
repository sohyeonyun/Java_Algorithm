import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987 {

	static int N;
	static int MAX = 0;
	static int[][] eggs;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		eggs = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken());
			eggs[i][1] = Integer.parseInt(st.nextToken());
		}

		dfs(0);

		System.out.println(MAX);
	}

	private static void dfs(int idx) {
		// 가장 오른쪽 계란 들었으면 종료
		if (idx == N) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (eggs[i][0] <= 0)
					cnt++;
			}
			MAX = Math.max(MAX, cnt);
			return;
		}

		boolean crashed = false;
		// 깰 계란 선택하기
		for (int i = 0; i < N; i++) {
			// 자기 자신은 못깸
			if (idx == i)
				continue;
			// 깨진 계란 아니면 깬다
			if (eggs[i][0] > 0 && eggs[idx][0] > 0) {
				crashed = true;
				// 계란 깨기 ㅎ
				eggs[i][0] -= eggs[idx][1];
				eggs[idx][0] -= eggs[i][1];
				// 재귀
				dfs(idx + 1);
				// 계란 되돌리기 ㅎ
				eggs[i][0] += eggs[idx][1];
				eggs[idx][0] += eggs[i][1];
			}
		}
		// 깨지지 않은 다른 계란이 없거나 손에 든 계란이 깨졌거나
		if (!crashed) {
			dfs(idx + 1);
		}

	}

}
