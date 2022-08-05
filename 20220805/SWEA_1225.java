package day3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * SWEA 1225 - 암호생성기
 * 큐
 */

public class SWEA_1225 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		// 테케 10개
		for (int t = 1; t <= 10; t++) {
			// 큐 사용
			Queue<Integer> q = new LinkedList<>();

			// 입력 받기
			sc.nextLine();
			String[] inputs = sc.nextLine().split(" ");

			// 입력 값을 큐에 삽입
			for (int i = 0; i < inputs.length; i++) {
				q.add(Integer.parseInt(inputs[i]));
			}

			// 감소할 숫자
			int cnt = 0;
			while (true) {
				// cnt는 1 ~ 5의 숫자
				cnt = (cnt % 5 + 1);
				// cnt를 감소시킨다.
				int num = q.poll() - cnt;
				// 0보다 작거나 같아질 경우 끝
				if (num <= 0) {
					q.add(0);
					break;
				}
				// 맨뒤로 보낸다.
				q.add(num);
			}

			System.out.printf("#%d ", t);
			for (int i = 0; i < 8; i++) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();

		}
	}

}
