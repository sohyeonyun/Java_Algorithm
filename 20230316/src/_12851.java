import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 숨바꼭질 2
public class _12851 {

	static int N, K;
	static int[] v = new int[100_001];
	static int min_time = Integer.MAX_VALUE;
	static int max_num = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		if(K <= N) {
			System.out.println(N - K);
			System.out.println(1);
			return;
		}
		
		Arrays.fill(v, -1);
		bfs();

		// 결과 출력
		System.out.println(min_time);
		System.out.println(max_num);
	}

	private static void bfs() {

		int[] dx = { -1, 1, 2 };

		Queue<Data> q = new LinkedList<Data>();
		q.offer(new Data(N, 0));
		v[N] = 0; // 방문 체크 -> 이동 횟수 넣어서 큐에 더 넣을지 판단용

		while (!q.isEmpty()) {
			Data cur = q.poll();

			// 찾았다! 큐에 그만 넣기
			if (cur.pos == K) {
				min_time = cur.time; // 최소 횟수
				max_num = 1; // 최대 경우의 수
				break;
			}

			// -1, +1, *2
			for (int i = 0; i < 3; i++) {
				// *2의 효과
				if (i == 2) {
					dx[2] = cur.pos; // 2번 더하기 == 2 곱하기
				}
				// 이동해보기
				int next_pos = cur.pos + dx[i];
				if (0 <= next_pos && next_pos <= 100_000) { // 범위 안이면
					// 방문 안했거나, 같은 이동 횟수면
					if (v[next_pos] == -1 || v[next_pos] == cur.time + 1) {
						q.offer(new Data(next_pos, cur.time + 1));
						v[next_pos] = cur.time + 1;
					}
				}
			}
		}

		// 경우의 수를 구하기 위해, 최소 시간 대의 나머지 것도 확인해보자.
		while (!q.isEmpty()) {
			Data cur = q.poll();

			// 시간 넘어가면
			if (cur.time > min_time) {
				break;
			}

			// 찾았다 !
			if (cur.pos == K) {
				max_num++;
			}
		}
	}

	static class Data {
		int pos, time;

		public Data(int pos, int time) {
			super();
			this.pos = pos;
			this.time = time;
		}
	}

}
