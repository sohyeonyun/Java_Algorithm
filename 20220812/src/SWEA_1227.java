import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 미로2 - BFS
 */
class Position {
	int x;
	int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_1227 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (true) {
			// 테케 10번
			int T = Integer.parseInt(br.readLine());

			Position start = null;
			// 맵 입력 받기
			int[][] map = new int[100][100];
			for (int i = 0; i < 100; i++) {
				String line = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = line.charAt(j) - '0';
					// 시작점 저장
					if (map[i][j] == 2) {
						start = new Position(i, j);
					} 
				}
			}

			Queue<Position> q = new LinkedList<>();
			q.offer(start);

			boolean able = false;
			LOOP: while (!q.isEmpty()) {
				Position cur = q.poll();
				// 방문 체크
				map[cur.x][cur.y] = 1;
				// 다음 위치
				int x = 0, y = 0;
				// 상하좌우
				for (int i = 0; i < 4; i++) {
					// 다음 위치
					x = cur.x + dx[i];
					y = cur.y + dy[i];
					// 맵 벗어나면 다음꺼
					if (x < 0 || x >= 100 || y < 0 || y >= 100)
						continue;
					// 도착지점(3)이면 탐색 종료
					if (map[x][y] == 3) {
						able = true;
						break LOOP;
					} else if (map[x][y] == 0) {
						// 큐에 저장
						q.offer(new Position(x, y));
					}
				}
				
			}

			System.out.printf("#%d %d%n", T, able ? 1 : 0);
			
			if (T == 10)
				break;

		}
	}

}
