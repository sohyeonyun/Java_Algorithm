import java.math.BigDecimal;
import java.util.Scanner;

/*
 * 조합 nCm
 */
public class BOJ_2407 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		if(n / 2 < m) {
			m = n - m;
		}

		// nCr = n-1Cr + n-1Cr-1
		// nCr = nPr / r!
		// 		= n! / (n-r)! / r!
		// 		= n * n-1 * ... * n-r+1 / r!
		
		BigDecimal ans = new BigDecimal("1");
		for (int i = 0; i < m; i++) {
			BigDecimal tmp = new BigDecimal((n - i) + "");
			ans = ans.multiply(tmp);
		}
		for (int i = 1; i <= m; i++) {
			BigDecimal tmp = new BigDecimal(i + "");
			ans = ans.divide(tmp);
		}
		
		System.out.println(ans);
	}

}
