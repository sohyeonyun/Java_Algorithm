import java.util.Scanner;

/*
 * 피보나치 수 6 - ???
 */
public class BOJ_11444 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		
		long a = 0;
		long b = 1; 
		long c = 1;
		for(int i=2; i<=N; i++) {
			c = (a + b) % 1_000_000_007;
			a = b;
			b = c;
		}
		System.out.println(c);
	}

}
