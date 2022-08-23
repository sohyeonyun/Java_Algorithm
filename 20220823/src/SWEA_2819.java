import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_2819 {

	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static HashSet<String> set;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 맵 입력받기
			map = new int[4][4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			set = new HashSet<>();

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					move(i, j, "");
				}
			}
			
			System.out.printf("#%d %d%n", t, set.size());
		}
	}

	private static void move(int r, int c, String sb) {
		if (sb.length() == 7) {
			set.add(sb);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = r + dx[i];
			int ny = c + dy[i];
			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
				continue;
			move(nx, ny, sb + map[nx][ny]);
		}
	}

}
