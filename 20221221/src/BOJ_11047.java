import java.util.Scanner;

/*
 * 동전 0
 */
public class BOJ_11047 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] coins = new int[N];
		for(int i=0; i<N; i++) {
			coins[i] = sc.nextInt();
		}
		
		int answer = 0;
		// 큰 단위부터 사용해야 동전이 최소 개수
		for(int i=N-1; i>=0; i--) {
			// 합 맞춰졌으면 끝
			if(K == 0) {
				break;
			}
			// 사용한 동전 개수
			answer += K / coins[i];
			// 사용한 값 계산
			K %= coins[i];
		}
		
		System.out.println(answer);
	}

}
