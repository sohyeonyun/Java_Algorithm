import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 햄버거 다이어트
 */
public class SWEA_5215 {

	static int n, max_cal;
	static boolean[] isSelected;
	static int[] score, cal;
	static int MAX_score = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			MAX_score = 0;
			n = sc.nextInt();
			max_cal = sc.nextInt();
			score = new int[n];
			cal = new int[n];
			for (int i = 0; i < n; i++) {
				score[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}

			isSelected = new boolean[n];
			subset(0);

			System.out.printf("#%d %d%n", t, MAX_score);
		}
	}

	static void subset(int cnt) {
		if (cnt == n) {
			int scores = 0, cals = 0;
			for (int i = 0; i < n; i++) {
				if (isSelected[i]) {
					scores += score[i];
					cals += cal[i];
				}
			}
			if (cals < max_cal) {
				MAX_score = Math.max(MAX_score, scores);
			}
			return;
		}

		isSelected[cnt] = true;
		subset(cnt + 1);
		isSelected[cnt] = false;
		subset(cnt + 1);
	}

}
