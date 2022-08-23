import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {

	static int r, n;
	static char[] types, answer;
	static boolean[] check = new boolean[125];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		types = new char[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			types[i] = st.nextToken().charAt(0);
		}

		answer = new char[r];

		// 정렬된 암호
		Arrays.sort(types);

		// 모음인지 확인 위해 생성 (97~122 중 모음만 true)
		check['a'] = check['e'] = check['i'] = check['o'] = check['u'] = true;

		dfs(0, 0, 0, 0);

		System.out.println(sb);
	}

	private static void dfs(int cnt, int start, int mo, int ja) {
		if (cnt == r) {
			if (mo >= 1 && ja >= 2) {
				sb.append(answer).append('\n');
			}
			return;
		}

		for (int i = start; i < n; i++) {
			answer[cnt] = types[i];
			dfs(cnt + 1, i + 1, mo + (check[types[i]] ? 1 : 0), ja + (check[types[i]] ? 0 : 1));
		}
	}

}
