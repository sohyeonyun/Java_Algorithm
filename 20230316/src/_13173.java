import java.util.Scanner;

// 시그마
public class _13173 {

	/*
	 * 최종 결과 S1/N1 + S2/N2 + ... + SM/NM = ?
	 * 
	 * 1. 통분한다.
	 * 2. 1번을 기약분수(a/b 꼴)로 나타낸다.
	 * 		기약분수는 최소공배수로 나누기
	 * 3. (a * b^-1) (mod 10억)을 대신 출력하라고 한다. 
	 * 4. 페르마의 소정리를 이용해 식을 변환한다. 
	 * 		b^(-1) == b^(X - 2) (mod X)
	 * 5. a와 4번 결과를 곱한다. 
	 * 
	 */
	
	static int mod = 1_000_000_007;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();

		long S = 0; // 분자
		long N = 1; // 분모
		
		// 1. 통분하기
        for(int i=0;i<M;i++) {
            int n = sc.nextInt();
            int s = sc.nextInt();
            S = s * N + S * n;
            N *= n;
            S %= mod;
            N %= mod;
        }
		
		// 2. 페르마 소정리
        if(S % N != 0) {
        	System.out.println(S * pow(N, mod - 2) % mod);
        } else {
        	System.out.println(S / N);
        }
	}
	
	private static long pow(long a, long n) {
	    if (n == 1) {
	    	return a;
	    }
	    
	    long tmp = pow(a, n / 2);
	    if (n % 2 == 0)
	        return tmp * tmp % mod;
	    else
	        return a * tmp % mod * tmp % mod;
	}
}
