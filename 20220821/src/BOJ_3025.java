import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 돌던지기 - 플4
 */
public class BOJ_3025 {

	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int y = Integer.parseInt(br.readLine()) - 1;
			move(y);
		}

		// 맵 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}
		
		bw.flush();

	}

	static void move(int start) {
		int x = 0; // 행 위치
		int y = start;

		// 중력에 의해 아래로 이동..
		while (true) {
			int nx = x + 1;
			
			// 인덱스 벗어나면 멈춤
			if(nx == R) break;

			// 이동할 칸이 벽이면 멈춤
			if (map[nx][y] == 'X') {
				break;
			} else if (nx == R - 1 && map[nx][y] == '.') { // 이동할 칸이 마지막 줄이고 빈칸이면 멈춤
				x = nx;
				break;
			} else if (map[nx][y] == '.') { // 빈칸이면 이동
				x = nx;
			} else { // 돌이 있으면
				// 왼쪽
				if (y > 0 && map[x][y - 1] == '.' && map[nx][y - 1] == '.') {
					x = nx;
					y--;
				} else if (y < C - 1 && map[x][y + 1] == '.' && map[nx][y + 1] == '.') { // 오른쪽
					x = nx;
					y++;
				} else {
					break;
				}
			}
		}

		map[x][y] = 'O';

	}

}
