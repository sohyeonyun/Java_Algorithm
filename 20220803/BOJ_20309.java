import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20309 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int i = 0;
		for (i = 0; i < n; i++) {
			if (i % 2 == 0) {
				if (arr[i] % 2 != 1) {
					break;
				}
			} else {
				if (arr[i] % 2 != 0) {
					break;
				}
			}

		}
		if (i != n) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}

	}

}
