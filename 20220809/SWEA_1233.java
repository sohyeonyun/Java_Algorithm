
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SWEA_1233 {
	static List<String> tree;
	static int answer = 1;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= 10; t++) {
			answer = 1;
			int n = Integer.parseInt(sc.nextLine());

			tree = new ArrayList<>();
			// tree 입력받기
			tree.add("");
			for (int i = 1; i <= n; i++) {
				String s = sc.nextLine().split(" ")[1];
				tree.add(s);
			}

			post(1);
			
			System.out.printf("#%d %d%n", t, answer);
		}

	}

	static void post(int index) {
		if (index * 2 >= tree.size())
			return;

		int left = index * 2;
		int right = index * 2 + 1;

		post(left);
		post(right);

		// 왼쪽 오른쪽이 숫자여야 한다.
		if (!isNum(tree.get(left).charAt(0)) || !isNum(tree.get(left).charAt(0))) {
			answer = 0; // false
			return;
		}
		// 가운데는 문자여야 한다.
		if (isNum(tree.get(index).charAt(0))) {
			answer = 0; // false
			return;
		}

		// 가운데를 숫자로 바꿔준다.
		tree.set(index, "1");
	}

	static boolean isNum(char c) {
		switch (c) {
		case '+':
		case '-':
		case '*':
		case '/':
			return false;
		default:
			return true;
		}
	}

}
