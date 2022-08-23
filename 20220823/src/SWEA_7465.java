import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_7465 {

	static int[] p;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			// 1~n까지의 집합 생성
			makeSet(n);

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// a와 b 간에 부모를 합친다.
				unionSet(a, b);
			}

			// 서로 다른 부모 개수 찾기 위해 set을 이용
			HashSet<Integer> set = new HashSet<>();
			for (int i = 1; i <= n; i++) {
				set.add(findSet(i));
			}

			// 결과
			sb.append("#").append(t).append(" ").append(set.size()).append('\n');
		}

		System.out.print(sb);
	}

	static void makeSet(int n) {
		p = new int[n + 1];
		// 자기 자신이 부모가 되도록 집합 생성
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
	}

	static int findSet(int x) {
		if (x == p[x])
			return x;
		// 부모 찾아서 저장해준다. 경로 압축 
		return p[x] = findSet(p[x]);
	}

	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x == y)
			return;
		// 부모 합친다.
		p[y] = x;
	}
}
