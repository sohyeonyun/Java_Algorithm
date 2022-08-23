import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 알파벳 - dfs
 */

public class BOJ_1987 {

	static int res = 0;
	static int r, c;
	static int[][] board;
	static boolean[] v = new boolean[30];
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new int[r][c];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				board[i][j] = s.charAt(j) - 'A';
			}
		}

		v[board[0][0]] = true;
		dfs(0, 0, 1);

		System.out.println(res);
	}

	private static void dfs(int x, int y, int cnt) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= r || ny < 0 || ny >= c)
				continue;
			if (v[board[nx][ny]])
				continue;

			v[board[nx][ny]] = true;
			dfs(nx, ny, cnt + 1);
			v[board[nx][ny]] = false;
		}

		res = Math.max(res, cnt);
	}

}
