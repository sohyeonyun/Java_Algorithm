import java.util.Scanner;

/*
 * N과 M (2)
 */
public class BOJ_15650 {

	static int N, M;
	static int[] nums;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		nums = new int[M];
		comb(0, 0);
	}

	private static void comb(int start, int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<N; i++) {
			nums[cnt] = i + 1; // 자연수
			comb(i + 1, cnt + 1);
		}
		
	}

}
