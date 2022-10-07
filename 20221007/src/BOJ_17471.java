import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 게리맨더링 
 */
public class BOJ_17471 {

	static int N;
	static int[] people;
	static boolean[] v;
	static boolean[][] connect;
	static ArrayList<Integer> list1, list2;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		people = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		connect = new boolean[N + 1][N + 1];
		connect1 = new boolean[N + 1][N + 1];
		connect2 = new boolean[N + 1][N + 1];
		for (int from = 1; from <= N; from++) {
			connect[from][from] = true;
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int to = Integer.parseInt(st.nextToken());
				connect[from][to] = true;
			}
		}

		v = new boolean[N + 1];
		subset(1); // 1번 인덱스부터 시행

		System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);

	}

	static int sum1, sum2, idx1, idx2;
	static boolean[][] connect1, connect2;

	private static void subset(int cnt) {

		// 부분집합 탐색 끝
		if (cnt == N + 1) {
			sum1 = sum2 = idx1 = idx2 = 0;
			for (int i = 0; i <= N; i++) {
				connect1[i] = Arrays.copyOf(connect[i], N + 1);
			}
			for (int i = 0; i <= N; i++) {
				connect2[i] = Arrays.copyOf(connect[i], N + 1);
			}

			// 각 리스트에 각 선거구 담음.
			list1 = new ArrayList<>();
			list2 = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (v[i]) {
					list1.add(i);
					sum1 += people[i]; // 선거구 인구수 합
					idx1 = i; // 선거구 인덱스
				} else {
					list2.add(i);
					sum2 += people[i];
					idx2 = i;
				}
			}

			// 한 쪽에 몰린 경우 불가능
			if (list1.isEmpty() || list2.isEmpty()) {
				return;
			}

			// 각 선거구 내에서 연결 표시
			int n = list1.size();
			for (int k = 0; k < n; k++) {
				int K = list1.get(k);
				for (int i = 0; i < n; i++) {
					int I = list1.get(i);
					for (int j = 0; j < n; j++) {
						int J = list1.get(j);
						if (connect1[I][K] && connect1[K][J]) {
							connect1[I][J] = true;
						}
					}
				}
			}

			n = list2.size();
			for (int k = 0; k < n; k++) {
				int K = list2.get(k);
				for (int i = 0; i < n; i++) {
					int I = list2.get(i);
					for (int j = 0; j < n; j++) {
						int J = list2.get(j);
						if (connect2[I][K] && connect2[K][J]) {
							connect2[I][J] = true;
						}
					}
				}
			}

			// 각 선거구끼리 연결되었는지 확인
			for (int idx : list1) {
				if (!connect1[idx][idx1]) {
					return;
				}
			}
			for (int idx : list2) {
				if (!connect2[idx][idx2]) {
					return;
				}
			}

			// 인구수 차이
			MIN = Math.min(MIN, Math.abs(sum1 - sum2));

			return;
		}

		// 부분집합
		v[cnt] = true;
		subset(cnt + 1);
		v[cnt] = false;
		subset(cnt + 1);
	}

}
