import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 배열 돌리기 3
 */
public class BOJ_16935 {
	static int n, m;
	static int[][] arr;

	// 상하 반전
	static void com1() {
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m; j++) {
				int tmp = arr[i][j];
				arr[i][j] = arr[n - i - 1][j];
				arr[n - i - 1][j] = tmp;
			}
		}
	}

	// 좌우 반전
	static void com2() {
		for (int j = 0; j < m / 2; j++) {
			for (int i = 0; i < n; i++) {
				int tmp = arr[i][j];
				arr[i][j] = arr[i][m - j - 1];
				arr[i][m - j - 1] = tmp;
			}
		}
	}

	// 오른쪽 90도 회전
	static void com3() {
		int[][] tmp = new int[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmp[j][n - i - 1] = arr[i][j];
			}
		}
		arr = tmp;
		// 가로 세로 크기 변경
		int t = n;
		n = m;
		m = t;
	}

	// 왼쪽 90도 회전
	static void com4() {
		int[][] tmp = new int[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = m - 1; j >= 0; j--) {
				tmp[m - j - 1][i] = arr[i][j];
			}
		}
		arr = tmp;
		// 가로 세로 크기 변경
		int t = n;
		n = m;
		m = t;
	}

	// 4분면 시계방향
	static void com5() {
		int[][] tmp = new int[n][m];
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmp[i][j + m / 2] = arr[i][j];
			}
		}
		for (int i = 0; i < n / 2; i++) {
			for (int j = m / 2; j < m; j++) {
				tmp[i + n / 2][j] = arr[i][j];
			}
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = m / 2; j < m; j++) {
				tmp[i][j - m / 2] = arr[i][j];
			}
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmp[i - n / 2][j] = arr[i][j];
			}
		}
		arr = tmp;
	}

	// 4분면 반시계방향
	static void com6() {
		int[][] tmp = new int[n][m];
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmp[i + n / 2][j] = arr[i][j];
			}
		}
		for (int i = 0; i < n / 2; i++) {
			for (int j = m / 2; j < m; j++) {
				tmp[i][j - m / 2] = arr[i][j];
			}
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = m / 2; j < m; j++) {
				tmp[i - n / 2][j] = arr[i][j];
			}
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = 0; j < m / 2; j++) {
				tmp[i][j + m / 2] = arr[i][j];
			}
		}
		arr = tmp;
	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		// 입력
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 연산 r번 수행
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			int command = Integer.parseInt(st.nextToken());
			switch (command) {
			case 1:
				com1();
				break;
			case 2:
				com2();
				break;
			case 3:
				com3();
				break;
			case 4:
				com4();
				break;
			case 5:
				com5();
				break;
			case 6:
				com6();
				break;
			}
		}

		// 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				bw.write(arr[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();

	}

}
