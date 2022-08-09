package day4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class SWEA_5432 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = Integer.parseInt(sc.nextLine());
		for (int t = 1; t <= T; t++) {
			System.out.printf("#%d %d%n", t, sol(sc.nextLine()));
		}
	}

	static int sol(String s) {
		int answer = 0; // 전체 막대기 개수
		int cnt = 0; // 레이저에 의해 잘린 막대기 개수

		for (int i = 0; i < s.length(); i++) {
			// 레이저 만나면 전체 막대기 개수 갱신
			if(s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
				answer += cnt;
				i++; // i + 1 번째 넘긴다.
			}
			else if(s.charAt(i) == '(') cnt++; // 막대 추가
			else {	// 막대 하나 끝
				cnt--;
				answer++;
			}
		}

		return answer;
	}

}
