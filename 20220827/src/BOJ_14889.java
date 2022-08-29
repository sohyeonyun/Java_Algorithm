import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 스타트와 링크 - 조합 
 */

public class BOJ_14889 {

	static int N;
	static int[][] map;
	static boolean[] v;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		v = new boolean[N];

		comb(0, 0);

		System.out.println(MIN);
	}

	private static void comb(int cnt, int start) {
		if (cnt == N / 2) {
			int sum1 = 0, sum2 = 0;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (v[i] && v[j])
						sum1 += map[i][j] + map[j][i];
					if (!v[i] && !v[j])
						sum2 += map[i][j] + map[j][i];
				}
			}
			MIN = Math.min(MIN, Math.abs(sum1 - sum2));
			return;
		}

		for (int i = start; i < N; i++) {
			if (v[i])
				continue;
			v[i] = true;
			comb(cnt + 1, i + 1);
			v[i] = false;
		}

	}

}
