import java.util.Scanner;

/*
 * 파도반 수열
 */
public class BOJ_9461 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		long[] arr = new long[101];
		arr[1] = arr[2] = arr[3] = 1;
		for(int i=4; i<=100; i++) {
			arr[i] = arr[i-2] + arr[i-3];
		}
		
		int T = sc.nextInt();
		while(T-- > 0) {
			int N = sc.nextInt();
			System.out.println(arr[N]);
		}
		
	}

}
