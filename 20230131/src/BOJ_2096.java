import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 내려가기
 * N * N 메모리 초과.. dp 
 */
public class BOJ_2096 {

	static int N;
	static int[][] map;
	static int MAX = 0, MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int[] max = new int[3];
		int[] min = new int[3];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x0 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			if (i == 0) {
				max[0] = min[0] = x0;
				max[1] = min[1] = x1;
				max[2] = min[2] = x2;
			} else {
				// 최댓값
				int preMax0 = max[0];
				int preMax2 = max[2];
				max[0] = Math.max(max[0], max[1]) + x0;
				max[2] = Math.max(max[1], max[2]) + x2;
				max[1] = Math.max(preMax0, Math.max(max[1], preMax2)) + x1;
				
				// 최솟값
				int preMin0 = min[0];
				int preMin2 = min[2];
				min[0] = Math.min(min[0], min[1]) + x0;
				min[2] = Math.min(min[1], min[2]) + x2;
				min[1] = Math.min(preMin0, Math.min(min[1], preMin2)) + x1;
			}
		}
		
		System.out.println(Math.max(max[0], Math.max(max[1], max[2])) + " " 
							+ Math.min(min[0], Math.min(min[1], min[2])));
		
	}

}
