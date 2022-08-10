import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16926 {

	static int n, m, r;
	static int[][] arr;

	static void rotate(int a, int b, int w, int h) {
//		System.out.println(a + " " + b + " " + w + " " + h);
		int tmp = arr[a][b];
		for (int j = b + 1; j < b + w; j++) {
//			System.out.println(a + " " + j);
			arr[a][j - 1] = arr[a][j];
		}
		for (int i = a + 1; i < a + h; i++) {
//			System.out.println(i + " " + (b + w - 1));
			arr[i - 1][b + w - 1] = arr[i][b + w - 1];
		}
		for (int j = b + w - 2; j >= b; j--) {
//			System.out.println((a + h - 1) + " " + j);
			arr[a + h - 1][j + 1] = arr[a + h - 1][j];
		}
		for (int i = a + h - 2; i > a; i--) {
			arr[i + 1][b] = arr[i][b];
		}
		arr[a + 1][b] = tmp;

	}

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력 받기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 총 회전 수
		for(int t=0; t<r; t++) {
			// 한 바퀴 회전
			for (int i = 0; i < Math.min(n, m) / 2; i++) {
				rotate(i, i, m - 2 * i, n - 2 * i);
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
