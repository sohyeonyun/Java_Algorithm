import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 15651 - n과 m (3)
 * 중복 순열 ( 1~n에서 m개 중복해서 나열함) 
 */
public class BOJ_15651 {

	static int n, m;
	static int[] nums;
	
	static BufferedWriter bw;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		nums = new int[m];

		repPerm(0);
		
		bw.flush();

	}

	static void repPerm(int cnt) throws IOException {

		if (cnt == m) {
			sb = new StringBuilder();
			for (int num : nums) {
				sb.append(num + " ");
			}
			bw.write(sb.toString() + "\n");
			return;
		}

		for (int i = 1; i <= n; i++) {
			nums[cnt] = i;
			repPerm(cnt + 1);
		}
	}

}
