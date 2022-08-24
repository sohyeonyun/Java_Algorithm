import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 요세푸스
 */

public class BOJ_11866 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			q.add(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while (!q.isEmpty()) {
			for (int i = 0; i < k - 1; i++) {
				q.add(q.poll());
			}
			if(q.size() == 1) {
				sb.append(q.poll());
				break;
			}
			sb.append(q.poll()).append(", ");
		}
		sb.append(">");
		
		System.out.println(sb);
	}

}
