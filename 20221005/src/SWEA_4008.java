import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 숫자 만들기
 */
public class SWEA_4008 {

	static int N, MAX, MIN;
	static int[] comm = new int[4];
	static int[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			comm[0] = Integer.parseInt(st.nextToken());
			comm[1] = Integer.parseInt(st.nextToken());
			comm[2] = Integer.parseInt(st.nextToken());
			comm[3] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			MAX = Integer.MIN_VALUE;
			MIN = Integer.MAX_VALUE;

			// 첫번째 꺼는 이미 sum에 적용된 상태
			dfs(1, nums[0]);

			System.out.println("#" + t + " " + (MAX - MIN));
		}

	}

	private static void dfs(int idx, int sum) {
		// 모든 숫자 적용 완료
		if(idx == N) {
			MAX = Math.max(MAX, sum);
			MIN = Math.min(MIN, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(comm[i] > 0) {
				comm[i]--; // 연산자 1개 사용
				if(i == 0) { // +
					dfs(idx+1, sum + nums[idx]);    //재귀호출
				}else if(i == 1) {// -
					dfs(idx+1, sum - nums[idx]);    //재귀호출
				}else if(i == 2) {// *
					dfs(idx+1, sum * nums[idx]);    //재귀호출
				}else if(i == 3) {// /
					dfs(idx+1, sum / nums[idx]);    //재귀호출
				}
				comm[i]++; // 연산자 사용안한 것으로 원상복귀
			}
		}
		
	}

}
