import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {

	static int[][] map;
	static int r, c;
	// 오위, 오른쪽, 오아
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };
	static int res = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = (s.charAt(j) == '.') ? 0 : 1;
			}
		}

		for (int i = 0; i < r; i++) {
			if(dfs(i, 0)) res++;
		}

		System.out.println(res);
	}

	private static boolean dfs(int x, int y) {
		if (y == c - 1) {
			return true;
		}
		int nx = x;
		int ny = y;
		for (int i = 0; i < 3; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
				continue;
			}
			if (map[nx][ny] == 1) {
				continue;
			}
			map[nx][ny] = 1; // 방문 표시
			if(dfs(nx, ny)) return true;
		}
		return false;
	}

}
