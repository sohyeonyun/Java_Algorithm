import java.util.Scanner;

/*
 * IOIOI 
 */
public class BOJ_5525 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int M = Integer.parseInt(sc.nextLine());
		char s[] = sc.nextLine().toCharArray();

		int result = 0;
		int cnt = 0;

		// O(N)으로 풀어야 한다. 
		for (int i = 0; i < M - 2; i++) {
			// IOI
			if (s[i] == 'I' && s[i + 1] == 'O' && s[i + 2] == 'I') {
				// IOI 연속 개수 증가 
				cnt++;
				
				// N개 찾으면 정답 업데이트 
				if(cnt == N) {
					result++;
					// 정답 체크해줬고 연속해서 IOI 나올 수 있으니 개수 감소 
					cnt--;
				}
				// O 건너뜀 
				i++;
			} else {
				// IOI 연속 개수 초기화 
				cnt = 0;
			}
		}
		
		System.out.println(result);

	}

}
