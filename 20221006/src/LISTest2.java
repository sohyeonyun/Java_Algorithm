import java.util.Arrays;
import java.util.Scanner;

public class LISTest2 {
    static int N;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] arr = new int[N];
        int[] dp = new int[N]; // 해당 길이(K)를 만족하는 자리에 오는 수의 최소값
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
//        O(NlogN)
        int size = 0;
        for(int i = 0; i < N; i++) {
            
            int pos = Arrays.binarySearch(dp,0,size, arr[i]);
            if(pos >= 0) { //찾으면 중복 숫자임으로 무시
                continue;
            }
            int insertPos = Math.abs(pos)-1;// 맨뒤, 또는 기존원소 대체
            dp[insertPos] = arr[i];
            if(size == insertPos) {
                size++;
            }
        }
        System.out.println(size);
        
    }
}