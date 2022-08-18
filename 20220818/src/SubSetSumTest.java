import java.util.Scanner;

public class SubSetSumTest {

	// N개의 자연수를 입력받고 목표합이 주어지면 목표합에 해당하는 부분집합을 출력
	static int N, tCnt, S;
	static int[] input;
	static int[] v;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt(); // 목표합
		input = new int[N];
		v = new int[N]; // 선택관리 배열
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		subSet(0);
		System.out.println("부분집합의 갯수 : " + tCnt);
	}

	static void subSet(int idx) { // 현재 위치
		// 종료 조건
		if (idx == N) { // 모든 원소를 고려해 봄(선택아님)
			tCnt++;
			for (int i = 0; i < N; i++) {
				System.out.print(v[i] == 1 ? input[i] : "X");
				System.out.print("\t");
			}
			System.out.println();
			return;
		}
		// 현재 원소 선택
		v[idx] = 1;
		subSet(idx + 1); // idx++ 하면 안됨
		// 현재 원소 미선택
		v[idx] = 0;
		subSet(idx + 1); // idx++ 하면 안됨
	}
}