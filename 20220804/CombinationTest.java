import java.util.Arrays;
import java.util.Scanner;

public class CombinationTest {

	static int N, R, tCnt; // 전역 변수로 활용
	static int[] nums, inputs;

	// nCr : n개의 입력받은 수 중에 r개를 모두 뽑아 순서없이 나열한 것
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		inputs = new int[N];
		nums = new int[R];

		// 실제 N개 만큼 입력
		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}
//        재귀 스타트
		comb(0, 0); // 선택된 갯수, 시작위치
		System.out.println("총 경우의 수 :" + tCnt);
	}

//    start : 시도할 수의 시작 위치
//    cnt: 직전까지 뽑은 조합에 포함된 갯수
	static void comb(int cnt, int start) {
		// 종료 조건
		if (cnt == R) { // 모든 수 뽑음
			tCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}

		// 가능한 모든 수에 시도(input 배열의 모든 수 시도)

		for (int i = start; i < N; i++) { // 선택지
			// start부터 처리 시도로 중복 추출을 방지 할 수 있음
			// 앞쪽에서 선택되지 않았다면 그 수를 사용
			nums[cnt] = inputs[i]; // 실제 입력받은 값
			// 다음수 뽑으로 가기
			comb(cnt + 1, i + 1); // i+1 !!!중요 start+1 아님
		}
	}

}