import java.util.Scanner;

public class BabyGin3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = 10;
		char[] temp = sc.nextLine().toCharArray();
		int[] num = new int[size];

		for (int i = 0; i < temp.length; i++) {
			num[temp[i] - '0']++;
		}

		// triplet 먼저 제거
		for (int i = 0; i < size; i++) {
			while (num[i] >= 3) {
				num[i] -= 3;
			}
		}

		// run check
		for (int i = 0; i < size - 2; i++) {
			if (num[i] != 0) {
				if (num[i] == num[i + 1] && num[i] == num[i + 2]) {
					num[i] = 0;
					num[i + 1] = 0;
					num[i + 2] = 0;
				}
			}
		}

		// 최종 확인
		boolean flag = true;
		for (int i = 0; i < size; i++) {
			if (num[i] != 0) {
				flag = false;
				break;
			}
		}

		if (flag)
			System.out.println("Baby-gin!");
		else {
			System.out.println("No");
		}

	}

}
