import java.util.Scanner;

/*
 * 참외밭
 */
class Input {
	int d;
	int l;

	public Input(int d, int l) {
		this.d = d;
		this.l = l;
	}
}

public class BOJ_2477 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 입력 받기
		int k = sc.nextInt();
		Input[] list = new Input[6];
		// 큰 직사각형의 두 변 길이와 그 때의 입력 인덱스
		int x_max = 0, y_max = 0;
		int x_idx = 0, y_idx = 0;

		for (int i = 0; i < 6; i++) {
			int dir = sc.nextInt();
			int len = sc.nextInt();
			list[i] = new Input(dir, len);

			// 가장 큰 값이 큰 직사각형의 두 변의 길이
			if (i % 2 == 0) {
				if (x_max < list[i].l) {
					x_idx = i;
					x_max = list[i].l;
				}
			} else {
				if (y_max < list[i].l) {
					y_idx = i;
					y_max = list[i].l;
				}
			}
		}

		// 작은 직사각형의 두 변 길이
		int x_min = list[(x_idx + 3) % 6].l;
		int y_min = list[(y_idx + 3) % 6].l;

		System.out.println((x_max * y_max - x_min * y_min) * k);
	}

}
