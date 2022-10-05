import java.util.Scanner;

public class BOJ_1107 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int target = sc.nextInt();
		int m = sc.nextInt();
		
		if(target == 100) {
			System.out.println(0);
			return;
		}

		boolean[] broken = new boolean[10];
		for (int i = 0; i < m; i++) {
			int n = sc.nextInt();
			broken[n] = true;
		}

		int result = Math.abs(target - 100); // 초기값 설정
		// 누를 수 있는 번호 모두에 대해 횟수 세어본다 
		for (int i = 0; i <= 999999; i++) {
			String str = String.valueOf(i);
			int len = str.length();

			boolean isBreak = false;
			// 각 자리마다 고장난 버튼 눌러야 하면 패쓰 
			for (int j = 0; j < len; j++) {
				if (broken[str.charAt(j) - '0']) {
					isBreak = true;
					break;
				}
			}
			// 고장난 버튼 누르지 않는다면 
			if (!isBreak) { 
				int min = Math.abs(target - i) + len; // i를 누른 후(len) target까지 이동하는 횟수(target - i)
				result = Math.min(min, result);
			}
		}
		System.out.println(result);
	}

}
