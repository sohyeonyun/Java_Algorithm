import java.util.Arrays;
import java.util.Scanner;

public class NextPermutationTest {
	// nPr 아님 nPn 이다.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] inputs = new int[N];

		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}
		// 0. 전처리 오름차순 정렬
		Arrays.sort(inputs);

		do {
			System.out.println(Arrays.toString(inputs)); // 현재 순열이 완성된 상태
		} while (np(inputs));
	}

	// 다음 순열이 존재하면 true, 다음 순열이 없으면 false;
	public static boolean np(int[] numbers) {// 배열의 상태를 근거로 순열 생성,
		int N = numbers.length;
//        1. 꼭대기 찾기
		int i = N - 1; // 마지막 위치에서 부터
//        i>0 첫번째 원소가아님을 판단, 음수 위치가 나올수 있음
		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			i--;
		}

		if (i == 0) { // 이미 만들수 있는 가장 큰순열이 만들어진 상태에서 검색함
			return false;
		}

//        2. 꼭대기 바로 앞에 있는 자리(i-1)와 크게 만들 교환 값 뒤쪽 찾기
		int j = N - 1;
		while (numbers[i - 1] >= numbers[j]) {
			j--;
		}
//        3. i-1위치값과, j위치값을 교환
		swap(numbers, i - 1, j);

//        4.  i 위치부터 마지막 까지의 수열을 가장 작은 형태의 오름차순 형태로 변경
//        이미 보이는 모습은 내림차순 모양이다.
//        그럼으로 맨앞과 맨뒤를 교환하면 더 빠르다.O(n/2)
		int k = N - 1; // 마지막 위치
		while (i < k) {
			swap(numbers, i, k);
			i++;
			k--;
		}
		return true;
	}

	// 교환 소스
	static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}