import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 숨바꼭질 - BFS
 */

public class BOJ_1697 {

	static int n, k;
	static int[] check = new int[100001];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();

		if (n == k) {
			System.out.println(0);
		} else {
			bfs(n);
		}
	}

	static void bfs(int num) {
		Queue<Integer> q = new LinkedList<>();
		q.add(num);
		check[num] = 1;

		while (!q.isEmpty()) {
			int cur = q.poll();

			// 방향 세 가지(-1, 1, 2배)
			for (int i = 0; i < 3; i++) {
				int next;
				
				if (i == 0) next = cur + 1;
				else if (i == 1) next = cur - 1;
				else next = cur * 2;

				// 동생 위치 찾았으면 종료
				if(next == k) {
					System.out.println(check[cur]);
					return;
				}
				
				// 동생 범위를 벗어나지 않고, 방문하지 않았으면
				if(next >= 0 && next < check.length && check[next] == 0) {
					q.add(next);
					check[next] = check[cur] + 1;
				}
			}
		}

	}

}
