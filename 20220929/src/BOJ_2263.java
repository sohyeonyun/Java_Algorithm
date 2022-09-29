import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2263 {

	public static int N;

	static int[] in_order;
	static int[] in_order_idx; // 중위 순회 루트들의 인덱스 정보
	static int[] post_order;

	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();

		in_order = new int[N + 1];
		in_order_idx = new int[N + 1];
		post_order = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			in_order[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			post_order[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++)
			in_order_idx[in_order[i]] = i;

		getPreOrder(0, N - 1, 0, N - 1);
		
		System.out.println(sb);
	}

	public static void getPreOrder(int in_start, int in_end, int p_start, int p_end) {
		if (in_start > in_end || p_start > p_end)
			return;

		// 루트 구하기. 후위 순회의 마지막 인덱스 p_end = 루트의 인덱스
		int root = post_order[p_end];
		sb.append(root + " ");

		// 중위 순회에서 루트의 인덱스 구하기
		int rootIdx = in_order_idx[root];
		// 중위 순회에서 루트 기준 왼쪽에 노드 개수 계산
		int left = rootIdx - in_start;

		// 좌측 자식 노드
		getPreOrder(in_start, rootIdx - 1, p_start, p_start + left - 1);

		// 우측 자식 노드
		getPreOrder(rootIdx + 1, in_end, p_start + left, p_end - 1);
	}

}
