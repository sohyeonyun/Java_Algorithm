import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1991 {

	static int N;
	static Node[] tree;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		tree = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0) - 'A';
			int right = st.nextToken().charAt(0) - 'A';
			tree[cur] = new Node(left, right);
		}
		
		sb = new StringBuilder();
		
		preorder(0);
		sb.append("\n");
		
		inorder(0);
		sb.append("\n");
		
		postorder(0);
		System.out.println(sb);

	}

	private static void postorder(int cur) {
		if (cur < 0 || cur >= N) {
			return;
		}

		postorder(tree[cur].left);
		postorder(tree[cur].right);
		sb.append((char) (cur + 'A'));
	}

	private static void inorder(int cur) {
		if (cur < 0 || cur >= N) {
			return;
		}
		inorder(tree[cur].left);
		sb.append((char) (cur + 'A'));
		inorder(tree[cur].right);
	}

	private static void preorder(int cur) {
		if (cur < 0 || cur >= N) {
			return;
		}
		sb.append((char) (cur + 'A'));
		preorder(tree[cur].left);
		preorder(tree[cur].right);
	}

	static class Node {
		int left, right;

		public Node(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}

	}

}
