import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 스도쿠
 */
public class BOJ_2239 {

	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		// 재귀
		dfs(0);

		// 출력
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	private static boolean dfs(int depth) {

		if (depth == 81) {
			// 제일 먼저 채운게 가장 작은 81자리수다.
			// true를 리턴해 전체 dfs를 모두 멈춰준다.
			return true; 
		}

		// 맵에서 현재 위치 (r, c)
		int r = depth / 9;
		int c = depth % 9;
		// 이미 맵 채워져 있으면 재귀
		if (map[r][c] != 0) {
			if (dfs(depth + 1)) {
				return true;
			}
			return false;
		}
		// 맵이 빈 칸이면 숫자 채움
		for (int x = 1; x <= 9; x++) {
			// 규칙에 위배되지 않는 경우
			if (isAble(r, c, x)) {
				map[r][c] = x; // 숫자 채운다.
				if (dfs(depth + 1)) { // 재귀
					return true;
				}
				map[r][c] = 0; // 숫자 상태 복귀
			}
		}
		
		return false;

	}

	private static boolean isAble(int r, int c, int x) {

		// 세로 확인
		for (int i = 0; i < 9; i++) {
			if (map[i][c] == x) {
				return false;
			}
		}
		// 가로 확인
		for (int j = 0; j < 9; j++) {
			if (map[r][j] == x) {
				return false;
			}
		}
		// 3 * 3 사각형 확인
		int rs = (r / 3) * 3;
		int cs = (c / 3) * 3;
		for (int i = rs; i < rs + 3; i++) {
			for (int j = cs; j < cs + 3; j++) {
				if (map[i][j] == x) {
					return false;
				}
			}
		}

		return true;
	}

}
