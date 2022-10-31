import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 트리의 부모 찾기
 */
public class BOJ_11725 {

	static ArrayList<Integer>[] list;
	static int[] root;
	static boolean[] v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		// 루트
		root = new int[N + 1];
		root[1] = 1;
		
		// 방문체크
		v = new boolean[N + 1];
		
		// 간선 입력받을 리스트
		list = new ArrayList[N + 1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}

		// 간선 입력 받기
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		// dfs
		dfs(1);

		// 출력
		for (int i = 2; i <= N; i++) {
			sb.append(root[i]).append("\n");
		}
		System.out.print(sb);

	}

	private static void dfs(int x) {
		// 방문체크
		v[x] = true;
		
		// 인접 노드 확인
		for(Integer i: list[x]) {
			if(v[i]) { // 방문 했으면 패스
				continue;
			}
			// 루트 체크
			root[i] = x;
			dfs(i);
		}
	}

}
