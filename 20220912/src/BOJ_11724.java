import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11724 {

	static int[] p;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 자기 자신을 부모로 초기화
		makeSet(N);

		// 노드 a, b 간에 부모를 합친다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b);
		}

		// 부모 배열 특성을 이용해 연결 개수 찾기
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (i == p[i]) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}

	static void makeSet(int N) {
		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
	}

	static int findSet(int x) {
		if (x == p[x])
			return x;
		return p[x] = findSet(p[x]);
	}

	static void union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x == y)
			return;
		p[y] = x;
	}

}
