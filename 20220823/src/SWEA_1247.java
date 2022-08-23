import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1247 {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int n;
	static Pos[] order, people;
	static Pos company, home;
	static boolean[] v;
	static int result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = Integer.MAX_VALUE;

			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			people = new Pos[n];
			for (int i = 0; i < n; i++) {
				people[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			order = new Pos[n];
			v = new boolean[n];
			perm(0);

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}

	// n! 경우의 수 구한다.
	static void perm(int cnt) {
		if (cnt == n) {
			// 회사 -> 고객들 -> 집 까지의 경로 구한다.
			int sum = 0;
			for (int i = 0; i < n - 1; i++) {
				sum += getDist(order[i], order[i + 1]);
			}
			sum += getDist(company, order[0]);
			sum += getDist(home, order[n - 1]);
			
			result = Math.min(result, sum);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (v[i])
				continue;
			order[cnt] = people[i];
			v[i] = true;
			perm(cnt + 1);
			v[i] = false;
		}
	}

	static int getDist(Pos a, Pos b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

}
