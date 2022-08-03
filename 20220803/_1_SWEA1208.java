import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// SWEA 1208 - Flatten
public class _1_SWEA1208 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			// 입력
			int count = Integer.parseInt(sc.nextLine());

			ArrayList<Integer> arr = new ArrayList<>();
			String[] nums = sc.nextLine().split(" ");
			for (String num : nums) {
				arr.add(Integer.parseInt(num));
			}
			int n = arr.size();
			Collections.sort(arr);

			int diff = arr.get(n - 1) - arr.get(0);
			for (int i = 0; i < count; i++) {
				arr.set(0, arr.get(0) + 1);
				arr.set(n - 1, arr.get(n - 1) - 1);
				Collections.sort(arr);
				diff = arr.get(n - 1) - arr.get(0);
			}
			System.out.printf("#%d %d%n", t, diff);

		}
	}

}
