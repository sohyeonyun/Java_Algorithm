import java.util.Scanner;

public class SWEA_7699 {

	static int r, c;
	static char[][] map;
	static boolean[] v;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int res;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 최댓값
			res = 0;
			// 알파벳 방문 체크 배열
			v = new boolean[30];
			
			// 입력 받기
			r = sc.nextInt();
			c = sc.nextInt();
			sc.nextLine();
			map = new char[r][c];
			for (int i = 0; i < r; i++) {
				map[i] = sc.nextLine().toCharArray();
			}

			// 시작 위치 방문 체크
			v[map[0][0] - 'A'] = true;
			dfs(0, 0, 1);
			
			// 출력
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(sb);

	}

	private static void dfs(int x, int y, int cnt) {

		// 네방향
		for (int i = 0; i < 4; i++) {
			// 다음 위치
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 맵 벗어나거나 방문했ㅇ면 패스
			if (nx < 0 || nx >= r || ny < 0 || ny >= c || v[map[nx][ny] - 'A']) {
				continue;
			}
			// 이동
			v[map[nx][ny] - 'A'] = true;
			dfs(nx, ny, cnt + 1);
			v[map[nx][ny] - 'A'] = false;
		}
		
		res = Math.max(res, cnt);
	}

}
