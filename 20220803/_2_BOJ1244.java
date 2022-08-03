import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// BOJ 1244 - 스위치 켜고 끄기 (구현)
public class _2_BOJ1244 {

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		// 입력받기
		int n = sc.nextInt();
		int[] switches = new int[n + 1]; // 스위치 번호 1 ~ n
		for (int i = 1; i < n + 1; i++) {
			switches[i] = sc.nextInt();
		}

		int sNum = sc.nextInt();
		int[][] students = new int[sNum][2]; // { 성별, 받은수 }
		for (int i = 0; i < sNum; i++) {
			students[i][0] = sc.nextInt();
			students[i][1] = sc.nextInt();
		}

		// 학생 순서대로 스위치 조작
		for (int i = 0; i < sNum; i++) {
			int gender = students[i][0];
			int pos = students[i][1];

			// 남학생이면
			if (gender == 1) {
				// number의 배수들을 스위치
				int k = pos;
				while (pos <= n) {
					switches[pos] = 1 - switches[pos];
					pos += k;
				}
			} else { // 여학생이면
				// pos 위치 스위치
				switches[pos] = 1 - switches[pos];
				int left = pos;
				int right = pos;
				// pos 좌우로 대칭이면 스위치
				while (true) {
					// pos 좌우 인덱스
					left--;
					right++;
					// 스위치 번호 넘어가면 종료
					if (left < 1 || right > n) {
						break;
					}
					// 좌우가 다르면 종료
					if (switches[left] != switches[right]) {
						break;
					}
					switches[left] = 1 - switches[left];
					switches[right] = 1 - switches[right];
				}
			}
//			System.out.println(Arrays.toString(switches));
		}

		// 스위치 결과 출력
		for (int i = 1; i <= n; i++) {
			System.out.print(switches[i] + " ");
			if (i % 20 == 0) {	// i==20 아니다 
				System.out.println();
			}
		}

	}

}