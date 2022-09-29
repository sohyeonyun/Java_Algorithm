import java.util.Arrays;
import java.util.Scanner;

public class CoinChange_DP2 {
//    4, 6 원의 최적해
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        int[] dp = new int[money+1]; // i번째 까지의 최소 동전 갯수의 합
        dp[0] = 0;  //0 개
        int INF = 100000; //MAXVALUE 오버플로우 조심
//        int INF = Integer.MAX_VALUE; 
        for(int i=1; i<=money; ++i) {
            int min = INF;
            if(i >=4) {
                min = Math.min(min, dp[i-4] + 1);
            }
            if(i >=6) {
                min = Math.min(min, dp[i-6] + 1);
            }
            dp[i] = min;
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[money]== INF? -1 : dp[money]);
    }

}