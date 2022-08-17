import java.util.Scanner;

/*
 * 쿼드트리
 */

public class BOJ_1992 {

	static int[][] map;
	static String answer = "";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 입력받기
		int n = Integer.parseInt(sc.nextLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		// 분할정복
		quadTree(0, 0, n);
		System.out.println(answer);
	}

	static void quadTree(int x, int y, int n) {

		boolean same = true;
		int flag = map[x][y];
		// 분할된 구역이 모두 같은 숫자인지 확인한다.
		LOOP: for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (map[i][j] != flag) {
					same = false;
					break LOOP;
				}
			}
		}

		// 모두 같은 숫자이면 결과에 추가하고 리턴
		if (same) {
			answer += flag;
			return;
		} else { // 다른 숫자이면 더 쪼갠다.
			answer += "(";
			quadTree(x, y, n / 2);
			quadTree(x, y + n / 2, n / 2);
			quadTree(x + n / 2, y, n / 2);
			quadTree(x + n / 2, y + n / 2, n / 2);
			answer += ")";
		}

	}

}
