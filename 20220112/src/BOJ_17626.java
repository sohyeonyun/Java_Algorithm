import java.util.Scanner;

/*
 * BOJ 17626 - Four Squares
 */
public class BOJ_17626 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[50001];
		
		dp[1] = 1; 
				
		for(int i=1; i<=N; i++) {
			// i=6 => j=1, 2, 3
			// i=25 => j=1, 2, 3, 4, 5
			int tmp = 50000;
			for(int j=1; j <= (int)Math.sqrt(i); j++) {
				tmp = Math.min(tmp, dp[i - j * j]);
			}
			
			dp[i] = tmp + 1;
		}
		
		System.out.println(dp[N]);
	}

}
