import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289 {

	static int[] p;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			// 각자 집합 만들기
			makeSet(n);

			// m 번의 연산
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (k == 0) { // 합집합
					unionSet(a, b);
				} else { // 같은 집합인지 확인
					if(findSet(a) == findSet(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append('\n');
		}
		
		System.out.println(sb);

	}

	static void makeSet(int n) {
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
	}

	static int findSet(int x) {
		if (x == p[x])
			return x;
		return p[x] = findSet(p[x]);
	}
	
	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if(x == y) return;
		
		p[y] = x;
	}

}
