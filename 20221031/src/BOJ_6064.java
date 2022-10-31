import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 카잉 달력
 */
public class BOJ_6064 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int endday = lcm(M, N);
			int result = -1; // 실패할 경우
			
			int i;
			for (i = x; i <= endday; i += M) { // x는 M만큼 건너뜀
				// i를 N으로 나눈 나머지가 y 라면 만족
				// y == N 인 경우에는, i가 N으로 나눠떨어져야 만족
				if((y == N && i % N == 0) || (i % N == y)) {
					result = i;
					break;
				}
			}
			
			System.out.println(result);
		}

	}

	// 최대 공약수
	public static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	// 최소 공배수
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

}
