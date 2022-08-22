import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1759 {

	static int R, N;
	static char[] types;
	static char[] orders;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[] s = sc.nextLine().split(" ");
		R = Integer.parseInt(s[0]);
		N = Integer.parseInt(s[1]);

		types = new char[N];
		orders = new char[R];

		s = sc.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			types[i] = s[i].charAt(0);
		}

		Arrays.sort(types);

		comb(0, 0);

	}

	private static void comb(int cnt, int start) {

		if (cnt == R) {
			int mo = 0;
			for (int i = 0; i < R; i++) {
				if (orders[i] == 'a' || orders[i] == 'e' || orders[i] == 'i' || orders[i] == 'o' || orders[i] == 'u') {
					mo++;
				}
			}
			if (mo >= 1 && (R - mo) >= 2) {
				StringBuilder sb = new StringBuilder();
				for (char c : orders) {
					sb.append(c);
				}
				System.out.println(sb);
			}
			return;
		}

		for (int i = start; i < N; i++) {
			orders[cnt] = types[i];
			comb(cnt + 1, i + 1);
		}
	}

}
