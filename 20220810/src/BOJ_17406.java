import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Input {
	int r;
	int c;
	int s;

	Input(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class BOJ_17406 {
	static int[][] arr, origin; // 2차원 배열 저장할 곳 (연산 사용할 곳, 원본 값 저장할 곳)
	static Integer[] orders; // 재귀 돌면서 현재 순서 저장
	static boolean[] isSelected;
	static List<Input> commandList; // 입력 받은 명령어들 저장
	static List<Integer[]> orderList; // 순열 결과 저장
	static int k; // 회전 연산 횟수

	// 2차원 배열 회전 메서드
	static void rotate(int r, int c, int s) {
		for (int k = 1; k <= s; k++) {
			int x = r - 1 - k;
			int y = c - 1 - k;
			int tmp = arr[x][y];
			for (int i = x + 1; i < x + 2 * k + 1; i++) {
				arr[i - 1][y] = arr[i][y];
			}
			for (int j = y + 1; j < y + 2 * k + 1; j++) {
				arr[x + 2 * k][j - 1] = arr[x + 2 * k][j];
			}
			for (int i = x + 2 * k - 1; i >= x; i--) {
				arr[i + 1][y + 2 * k] = arr[i][y + 2 * k];
			}
			for (int j = y + 2 * k - 1; j >= y; j--) {
				arr[x][j + 1] = arr[x][j];
			}
			arr[x][y + 1] = tmp;
		}

	}

	// n! 경우의 수들을 각 경우마다 orderList에 배열로 저장한다.
	static void perm(int cnt) {
		if (cnt == k) {
			orderList.add(Arrays.copyOf(orders, orders.length));
			return;
		}
		for (int i = 0; i < k; i++) {
			if (isSelected[i])
				continue;
			orders[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// k 개 연산 입력 받기
		commandList = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			commandList.add(new Input(r, c, s));
		}

		// k 개 연산의 순서 모두 구함.
		isSelected = new boolean[k];
		orders = new Integer[k];
		orderList = new ArrayList<>();
		perm(0);

		// 2차원 원본 배열 저장할 곳
		origin = new int[n][m];
		// origin <- arr 2차원 배열 원본 복사해두기
		for (int i = 0; i < n; i++) {
			System.arraycopy(arr[i], 0, origin[i], 0, m);
		}

		// k 개의 연산을 순서를 바꾸며 수행
		int MIN = Integer.MAX_VALUE;
		for (int t = 0; t < orderList.size(); t++) {
			// 연산을 수행할 순서 배열
			Integer[] order = orderList.get(t);
			// 뽑아온 순서에 따라 연산 수행
			for (int i = 0; i < k; i++) {
				Input command = commandList.get(order[i]);
				rotate(command.r, command.c, command.s);
			}

//			// 배열 확인용 출력
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}

			// 배열의 최솟값 찾기
			for (int i = 0; i < n; i++) {
				int sum = 0;
				for (int j = 0; j < m; j++) {
					sum += arr[i][j];
				}
				MIN = Math.min(MIN, sum);
			}

			// origin -> arr 2차원 배열 원본 가져오기
			for (int i = 0; i < n; i++) {
				System.arraycopy(origin[i], 0, arr[i], 0, m);
			}
		}

		System.out.println(MIN);
	}
}
