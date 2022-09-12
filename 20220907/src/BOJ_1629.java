import java.util.Scanner;

/*
 * 곱셈
 * A^B % C
 */
public class BOJ_1629 {
	
	static int c;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		c = sc.nextInt();

		System.out.println(calc(a, b));

		/*
		 * a % c = ?
		 * 
		 * 3 % 5 = 3 
		 * 9 % 5 = 4 
		 * 27 % 5 = 2 
		 * 81 % 5 = 1
		 * 243 % 5 = 3
		 */

	}

	private static long calc(int a, int b) { // a^b
		
		if(b == 1) {
			return a % c;
		}
		
		long n = calc(a, b / 2);
		
		if(b % 2 == 1) {
			return (n * n % c) * a % c;
		}
		
		return n * n % c;
	}

}
