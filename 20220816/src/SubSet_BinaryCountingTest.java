import java.util.Scanner;

public class SubSet_BinaryCountingTest {

	static int[] map;
	static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N];
		for (int i = 0; i < N; i++) {
			map[i] = sc.nextInt();
		}
		generateSubSet();
	}

	private static void generateSubSet() {
		for (int flag = 0; flag <= 1 << N; flag++) { // 부분집합 만큼 반복
			// 현 비트열의 상태에 대해서 부분집합의 포함 여부 판단(N 만큼)
			for (int i = 0; i < N; i++) {
				if ((flag & (1 << i)) != 0) {// 현재 자리의 비트값이 선택되어 있는지 판단 여부
					System.out.print(map[i] + " ");
				}
			}
			System.out.println();
		}

	}

}