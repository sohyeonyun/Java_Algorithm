import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {

	static int r, c;
	static int[][] map;
	static int[] alpha = new int[26];
	static int MAX = 0;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				map[i][j] = arr[j] - 'A';
			}
		}

		// 처음 위치의 알파벳 저장
		alpha[map[0][0]]++;
		// 처음 위치와 리스트로 탐색
		dfs(0, 0, 1);

		System.out.println(MAX);
	}

	static void dfs(int x, int y, int cnt) {

		// 사방위 탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 맵 벗어나면 끝
			if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
				continue;
			}

			// 이미 방문한 알파벳이면 끝
			if (alpha[map[nx][ny]] == 1) {
				continue;
			}

			// 알파벳 리스트에 방문 표시 후 탐색
			alpha[map[nx][ny]] = 1;
			dfs(nx, ny, cnt + 1);
			alpha[map[nx][ny]] = 0;
		}

		// 탐색 끝났으면 최댓값 갱신
		MAX = Math.max(MAX, cnt);
	}

}
