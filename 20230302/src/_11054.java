import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11054 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp_left = new int[N]; // i원소를 마지막으로 하는 부분 수열의 최장 길이 값
		int[] dp_right = new int[N]; // i원소를 마지막으로 하는 부분 수열의 최장 길이 값

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 왼쪽부터 탐색, 오름차순
		for (int i = 0; i < N; i++) {
			dp_left[i] = 1;
			for (int j = 0; j < i; j++) { // 현재 인덱스 앞에꺼 보면서 dp 갱신
				if (arr[j] < arr[i] && dp_left[i] < dp_left[j] + 1) {
					dp_left[i] = dp_left[j] + 1;
				}
			}
		}
		// 오른쪽부터 탐색, 내림차순
		for (int i = N - 1; i >= 0; i--) {
			dp_right[i] = 1;
			for (int j = N - 1; j > i; j--) { // 현재 인덱스 앞에꺼 보면서 dp 갱신
				if (arr[j] < arr[i] && dp_right[i] < dp_right[j] + 1) {
					dp_right[i] = dp_right[j] + 1;
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			// 각 배열을 합쳐서 오름차순과 내림차순을 합함
			max = Math.max(max, dp_left[i] + dp_right[i]);
		}
		// 배열을 합하면서 가운데 원소가 1개씩 중복되므로 빼줌.
		System.out.println(max - 1);
		
	}

}
