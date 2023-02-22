import java.util.Arrays;
import java.util.Scanner;

/*
 * N과 M (5) - nPm
 */
public class BOJ_15654 {

	static int N, M;
	static boolean[] v;
	static int[] inputs, nums;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		inputs = new int[N];
		v = new boolean[N];
		nums = new int[M];
		
		for(int i=0; i<N; i++) {
			inputs[i] = sc.nextInt();
		}
		
		// 정렬
		Arrays.sort(inputs);
		
		// nPm
		perm(0);
	}

	private static void perm( int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!v[i]) {
				nums[cnt] = inputs[i];
				v[i] = true;
				perm( cnt + 1);
				v[i] = false;
			}
		}
	}

}
