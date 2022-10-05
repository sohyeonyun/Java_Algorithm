import java.util.Scanner;

/*
 * 잃어버린 괄호
 */

public class BOJ_1541 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String input = sc.nextLine();

		int sum = 0;
		String num = "";
		boolean minus = false;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (c == '-' || c == '+') {
				// 한 번 빼기면 계속 빼기하면됨.
				if (minus) {
					sum -= Integer.parseInt(num);
				} else {
					sum += Integer.parseInt(num);
				}
				// 빼기 표시
				if (c == '-') {
					minus = true;
				}
				// 입력 숫자 초기화
				num = "";
			} else {
				num += c;
			}
		}

		// 마지막 숫자 처리
		if (minus) {
			sum -= Integer.parseInt(num);
		} else {
			sum += Integer.parseInt(num);
		}

		System.out.println(sum);
	}

}
