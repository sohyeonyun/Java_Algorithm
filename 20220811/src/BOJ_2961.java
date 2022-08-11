import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Item {
	int s;
	int b;

	Item(int s, int b) {
		this.s = s;
		this.b = b;
	}
}

public class BOJ_2961 {
	static List<Item> list = new ArrayList<>();
	static int n;
	static int[] nums;
	static boolean[] v;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		for (int t = 0; t < n; t++) {
			int s = sc.nextInt();
			int b = sc.nextInt();
			list.add(new Item(s, b));
		}

		nums = new int[n];
		v = new boolean[n];
		subset(0);

		System.out.println(MIN);
	}

	static void subset(int idx) {
		if (idx == n) {
			calculate();
			return;
		}

		v[idx] = true;
		subset(idx + 1);
		v[idx] = false;
		subset(idx + 1);
	}

	static void calculate() {
		// 신맛, 쓴맛 계산
		int sss = 1;
		int bbb = 0;
		// 재료 하나라도 선택되었는지
		boolean isSelected = false;
		for (int i = 0; i < n; i++) {
			// 선택되었으면
			if (v[i]) {
				isSelected = true;
				Item item = list.get(i);
				sss *= item.s;
				bbb += item.b;
			}
		}
		if (isSelected) {
			// 최솟값 갱신
			MIN = Math.min(MIN, Math.abs(sss - bbb));
		}
	}
}
