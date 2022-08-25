import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_CB1 {

	static class Gate {
		int idx, n;

		public Gate(int idx, int n) {
			this.idx = idx;
			this.n = n;
		}
	}

	static ArrayList<Integer[]> orders = new ArrayList<>();
	static Integer[] order = new Integer[3];
	static boolean[] v;
	static int N, result;
	static Gate[] gates = new Gate[3]; // 게이트 위치, 낚시꾼 수

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 3! (게이트 입장 순서)
		v = new boolean[3];
		perm(0);

		// 테케
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 최솟값
			result = Integer.MAX_VALUE;

			// 입력 받기
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				gates[i] = new Gate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// 게이트 열 순서들 (012, 021, 102, ..., 210)
			for (Integer[] o : orders) {
				// 자리 비었는지 체크 배열
				v = new boolean[N + 1];
				order = o;
				// 문열기
				openDoor(0, v, 0);
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void openDoor(int cnt, boolean[] v, int sum) {
		// 세 게이트 다 열었으면 리턴
		if (cnt == 3) {
			// 최솟값 갱신
			result = Math.min(result, sum);
			return;
		}

		Gate cur = gates[order[cnt]];
		Data data;

		// 짝수 명이면 왼->오, 오->왼 두 경우 모두 해봐야함.
		if (cur.n % 2 == 0) {
			Gate copyCur = new Gate(cur.idx, cur.n);
			boolean[] copyV = Arrays.copyOf(v, v.length);
			data = setSeat(copyCur, new Data(copyV, sum), -1);
			openDoor(cnt + 1, data.v, data.sum);
		}
		
		Gate copyCur = new Gate(cur.idx, cur.n);
		boolean[] copyV = Arrays.copyOf(v, v.length);
		data = setSeat(copyCur, new Data(copyV, sum), 1);
		openDoor(cnt + 1, data.v, data.sum);

	}

	static class Data {
		boolean[] v;
		int sum;

		public Data(boolean[] v, int sum) {
			this.v = v;
			this.sum = sum;
		}
	}

	private static Data setSeat(Gate cur, Data data, int flag) {
		// 문과의 대칭 거리
		int d = 0;
		// 문 앞에서부터 양쪽으로 사람 심음
		while (cur.n > 0) {
			for (int i = 0; i < 2; i++) {
				int idx = cur.idx + d * flag; // -d or +d
				// 범위 안에 속하고 방문 안했으면, 낚시꾼 배치
				if (1 <= idx && idx <= N && !data.v[idx] && cur.n > 0) {
					data.v[idx] = true;
					cur.n--; // 낚시꾼 수 감소
					data.sum += d + 1; // 이동 거리
				}
				flag *= -1;
			}
			d++;
		}
		return data;
	}

	private static void perm(int cnt) {
		if (cnt == 3) {
			orders.add(Arrays.copyOf(order, 3));
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (v[i])
				continue;
			order[cnt] = i;
			v[i] = true;
			perm(cnt + 1);
			v[i] = false;
		}
	}

}
