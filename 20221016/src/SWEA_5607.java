import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607 {
	private static final int MOD = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            /*
             * nCr = n! / (r!(n-r)!)
             */
            
            // n!
            long fac[] = new long[n + 1];
            fac[0] = 1;
            for (int i = 1; i <= n; i++) {
            	fac[i] = (fac[i - 1] * i) % MOD;
            }

            /*
             * a^p = a  (mod p)
             * a^(p-2) = a^(-1)
             * 
             * 나누기는 mod 연산이 안되기 때문에
             * a를 (r!(n-r)!)로 놓고  a^(p-2)로 계산해야한다. 
             */
            // r!(n-r)!
            long bottom = (fac[r] * fac[n - r]) % MOD;
            long reBottom = fermat(bottom, MOD - 2);

            // n! / (r!(n-r)!)
            long ans = (fac[n] * reBottom) % MOD;
            System.out.println("#" + t + " " + ans);
        }
    }

    // 분할정복으로 거듭제곱 연산 n^k
    private static long fermat(long n, int k) {
        if (k == 0) {
        	return 1;
        }
        long x = fermat(n, k / 2) % MOD;
        if(k % 2 == 0) {
            return x * x % MOD;
        } else {
            return ((x * x) % MOD * n) % MOD;
        }
    }
}
