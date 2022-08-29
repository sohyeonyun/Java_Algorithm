import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int[] t = new int[N];
		int[] p = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		int MAX = 0;
		for (int i = N - 1; i >= 0; i--) {
			int time = i + t[i]; // 현재 일을 하고 난 뒤 다시 일할 수 있는 시간
			if (time <= N) { // 퇴사 전까지 끝낼 수 있으면
				dp[i] = Math.max(dp[time] + p[i], MAX); // 현재 일 수당 + time 시점 수당
				MAX = dp[i];
			} else {
				dp[i] = MAX; // i번째 시간까지 최대 수당 
			}
		}
		
		System.out.println(dp[0]);
	}

}
