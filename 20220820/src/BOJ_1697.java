import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1697 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		
		if(N == K) {
			System.out.println(0);
			return;
		}

		// 위치 범위 만큼 방문 배열 크기를 지정
		// 방문 배열에 현재 시간을 넣는다!!!
		int[] v = new int[100001];

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(N);
		v[N] = 0;

		while (!q.isEmpty()) {

			int cur = q.poll();

			// 동생을 찾으면 끝
			if (cur == K) {
				System.out.println(v[cur]);
				break;
			}

			// X - 1로 이동
			if (cur - 1 >= 0 && v[cur - 1] == 0) {
				q.offer(cur - 1);
				v[cur - 1] = v[cur] + 1;
			}

			// X + 1로 이동
			if (cur + 1 <= 100000 && v[cur + 1] == 0) {
				q.offer(cur + 1);
				v[cur + 1] = v[cur] + 1;
			}

			// X * 2로 이동
			if (cur * 2 <= 100000 && v[cur * 2] == 0) {
				q.offer(cur * 2);
				v[cur * 2] = v[cur] + 1;
			}
		}

	}

}
