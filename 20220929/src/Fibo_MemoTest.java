import java.util.Arrays;
import java.util.Scanner;

public class Fibo_MemoTest {

	static long[] calls1, calls2;
	static long[] dp; // 메모이제이션에 사용될 메모리 공간
	static long callCnt1, callCnt2;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		calls1 = new long[N + 1]; // 각 항을 계산하기 위한 수행횟수
		calls2 = new long[N + 1];

		dp = new long[N + 1];
		Arrays.fill(dp, -1); // 메모되지 않는 상태를 나타내는 값으로 초기화
		dp[0] = 0; // 기저값은 먼저 초기화를 설정하고 재귀 호출
		dp[1] = 1;

		System.out.println(fibo1(N));
		System.out.println(callCnt1);
		for (int i = 1; i <= N; i++) {
			System.out.println("fibo1(" + i + ") : " + calls1[i]);
		}
		System.out.println("======================================");

		System.out.println(fibo2(N));
		System.out.println(callCnt2);
		for (int i = 1; i <= N; i++) {
			System.out.println("fibo2(" + i + ") : " + calls2[i]);
		}

	}

	static long fibo1(int n) { // 메모하지 않는 버전
		callCnt1++;
		calls1[n]++;
		if (n <= 1) {
			return n;
		}
		return fibo1(n - 1) + fibo1(n - 2);
	}

	static long fibo2(int n) { // 메모하지 않는 버전
		callCnt2++;
		calls2[n]++;

		if (dp[n] == -1) {
			dp[n] = fibo2(n - 1) + fibo2(n - 2);
		}
		return dp[n];
	}

}