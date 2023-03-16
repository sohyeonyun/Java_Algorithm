import java.util.Scanner;

// 시그마
public class _13172 {

	/*
	 * 최종 결과 S1/N1 + S2/N2 + ... + SM/NM = ?
	 * 
	 * 각 분수의 값을 하나씩 구해보자. 
	 * 1. 먼저, 분수를 기약분수로 만든다. 
	 * 2. 기약분수를 mod X에 대해 정수로 치환할 수 있다. 
	 * 		S/N == 정수 (mod X) 
	 * 3. 페르마의 소정리를 이용해 식을 변환한다. 
	 * 		N^(-1) == N^(X - 2) (mod X) 
	 * 4. N^(X-2) (mod X) 값을 계산한다. 
	 * 5. 4는 mod X에 대해 1/N과 같은 값이다. 
	 * 6. 그렇다면... mod X에 대해.... 
	 * 		S/N = S * (4번결과) 
	 * 7. 6번을 각 케이스마다 더하면 끗
	 * 
	 */
	
	static int mod = 1_000_000_007;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();

		long answer = 0;

		for (int i = 0; i < M; i++) {
			int N = sc.nextInt();
			int S = sc.nextInt();
			// 0번. S / N이 나눠떨어지는 경우
			if (S % N == 0) {
				answer += (S / N) % mod;
				continue;
			}
			// 1번. S / N를 기약분수로 만들자.
			int a = gcd(S, N);
			S /= a;
			N /= a;
			// 4번. N^(X-2) 의 값을 구하자.
			long k = pow(N, mod - 2);
			// 6번. S/N = S * k
			answer += (S * k) % mod; // 더하면서도 mod 해줘...

		}
		
		System.out.println(answer % mod);

	}
	
	private static long pow(long a, long n) {
	    if (n == 1) {
	    	return a;
	    }
	    
	    long tmp = pow(a, n / 2);
	    if (n % 2 == 0)
	        return tmp * tmp % mod;
	    else
	        return a * tmp % mod * tmp % mod; // 중간에도 mod 해!!!!!!!!!!!
	}

	private static int gcd(int a, int b) {
		int tmp, n;

		// a에 큰 값을 위치시키기 위한 조건이다.
		if (a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}

		// b가 0 될때까지(a%b), 반복문을 돌게되고, b가 0인 순간의 a를 GCD로 판단하고 리턴한다
		while (b != 0) {
			n = a % b;
			a = b;
			b = n;
		}
		
		return a;
	}
}
