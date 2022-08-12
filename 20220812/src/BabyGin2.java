import java.util.Arrays;
import java.util.Scanner;

// 배열에 값 넣고 푼 내 풀이,,
// 카드 개수를 저장해서 푸는 방식까진 생각했으나,,, 정리가 안됐음
public class BabyGin2 {
	static int[] origin = new int[10];
	static int[] arr = new int[10]; // 0 ~ 9
	static int start = 9, end = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		origin = new int[10];
		arr = new int[10]; // 0 ~ 9
		for (int i = 0; i < 6; i++) {
			int num = s.charAt(i) - '0';
			arr[num]++;
			// 시작, 끝 포인터
			start = Math.min(start, num);
			end = Math.max(end, num);
		}
		origin = Arrays.copyOf(arr, 10);

		if (!runrun() && !runTri() && !tritri()) {
			System.out.println("false");
		} else {
			System.out.println("true");
		}
	}

	// run + run
	static boolean runrun() {
		// run+run인 경우, 앞에서부터와 뒤에서부터 차례로 연속되어야 함.
		if (start + 3 >= 10 || end - 3 < 0)
			return false;
		for (int i = start; i < start + 3; i++) {
			arr[i]--;
		}
		for (int i = end; i > end - 3; i--) {
			arr[i]--;
		}
		// 0이 아닌 값이 있으면 run+run 이 아님.
		for (int i = start; i <= end; i++) {
			if (arr[i] != 0) {
				arr = Arrays.copyOf(origin, 10); // 배열 원위치
				return false;
			}
		}
		System.out.println("run + run");
		return true;
	}

	// run + triplet
	static boolean runTri() {
		boolean triplet = false;
		// 3이상 값이면 triplet
		for (int i = start; i <= end; i++) {
			if (arr[i] >= 3) {
				arr[i] -= 3;
				triplet = true;
				break;
			}
		}
		// 3 이상인 값이 없으면(triplet이 없으면)
		if (!triplet) {
			arr = Arrays.copyOf(origin, 10); // 배열 원위치
			return false;
		}
		// run - start부터 end까지 연속적으로 1이어야함.
		for (int i = start; i <= end; i++) {
			// 연속적 1이면 true;
			if (arr[i] == 1 && arr[i + 1] == 1 && arr[i + 2] == 1) {
				System.out.println("run + triplet");
				return true;
			}
		}
		arr = Arrays.copyOf(origin, 10); // 배열 원위치
		return false;
	}

	static boolean tritri() {
		// tri + tri 이려면 배열 값이 모두 3의 배수다.
		for (int i = start; i <= end; i++) {
			if (arr[i] % 3 != 0) {
				return false;
			}
		}

		System.out.println("triplet + triplet");
		return true;
	}

}
