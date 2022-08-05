package day3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * BOJ 12891 - DNA 비밀번호
 * 슬라이딩 윈도우
 */

public class BOJ_12891 {

	// 부분 문자열의 ACGT 개수, 입력으로 들어온 최소 ACGT 개수
	static int[] nums = new int[4];
	static int[] minLen = new int[4];
	// 정답
	static int answer = 0;

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		// 입력받기
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();

		// 부분문자열에 포함돼야할 최소 개수
		String str = sc.nextLine();
		String[] tmp = sc.nextLine().split(" ");
		for (int i = 0; i < 4; i++) {
			minLen[i] = Integer.parseInt(tmp[i]);
		}

		// 부분문자열의 왼쪽, 오른쪽 인덱스
		int left = 0;
		int right = m - 1;
		// 시작 부분문자열 ACGT 개수
		for (int i = left; i < right + 1; i++) {
			nums[getIdx(str.charAt(i))]++;
		}
		if (isAble()) {
			answer++;
		}

		// 한 칸씩 이동하며 부분문자열의 개수를 확인
		while (true) {
			if (right >= n - 1)
				break;
			// left 위치에 있던 문자 개수 빼준다.
			nums[getIdx(str.charAt(left))]--;
			left++;
			// right 위치 옮기고 문자 개수 더한다.
			right++;
			nums[getIdx(str.charAt(right))]++;
			if (isAble()) {
				answer++;
			}
		}

		System.out.println(answer);

	}

	static boolean isAble() {
		// 최소 개수 확인
		int i;
		for (i = 0; i < 4; i++) {
			if (nums[i] < minLen[i]) {
				break;
			}
		}
		// 최소 개수 충족
		if (i == 4) {
			return true;
		}
		return false;
	}

	static int getIdx(char c) {
		switch (c) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		default:
			return 0;
		}
	}

}
