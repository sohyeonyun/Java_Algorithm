import java.util.Scanner;

/*
 * 평범한 배낭 
 */
public class BOJ_12865 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] items = new int[N + 1][2]; // weight, value
		for(int i=1; i<=N; i++) {
			items[i][0] = sc.nextInt();
			items[i][1] = sc.nextInt();
		}
		
		int[][] dp = new int[N + 1][K + 1]; // 가치 계산해 담기 
		// 0번째는 초기화를 위해 남겨놓음. 
		for(int i=1; i<=N; i++) { // 1 ~ N 까지의 아이템 하나씩 고려해서  
			int curWeight = items[i][0];
			int curValue = items[i][1];
			
			for(int w=1; w<=K; w++) { // 1 ~ K 까지의 무게에서 가치를 계산해보자. 
				// 현재 i번째 물건을 담을 수 있고, 안담았을경우(i-1번째까지고려) 가치보다 담았을때의 가치가 크면 담는다. 
				if(w >= curWeight && dp[i - 1][w] < (dp[i - 1][w - curWeight] + curValue)) {
					dp[i][w] = dp[i - 1][w - curWeight] + curValue;
				} else { // 못담으면 이전꺼 
					dp[i][w] = dp[i - 1][w];
				}
			}
		}
	
		System.out.println(dp[N][K]);
	}

}
