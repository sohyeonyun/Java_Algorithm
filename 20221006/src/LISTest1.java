import java.util.Scanner;

public class LISTest1 {
    static int N;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] arr = new int[N];
        int[] dp = new int[N]; //현재 원소를 끝으로 할 경우 최장증가수열길이값 저장
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
//        O(N2)
        int max = 0;
        for(int i = 0; i < N; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp[i] < dp[j]+1) {
                    dp[i] = dp[j]+1;
                }
            }
            //최대값 갱신하기
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
        
        
    }
}