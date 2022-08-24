import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * 단지번호붙이기
 */

public class BOJ_2667 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		
		// bfs 위해 큐 생성
		Queue<Point> q = new LinkedList<>();
		// 단지내 집의 수 저장
		List<Integer> result = new ArrayList<>();

		// 좌표마다 확인
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 빈 집이거나 이미 방문한 집이면 패스
				if (map[i][j] == 0)
					continue;
				
				// 한 집으로 만든다
				q.add(new Point(i, j));
				map[i][j] = 0; // 방문 표시
				// 같은 단지내 집 수
				int cnt = 1;
				while (!q.isEmpty()) {
					Point cur = q.poll();
					// 사방위
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						// 맵 벗어나거나 이미 방문했으면 패스
						if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 0)
							continue;
						// 방문 표시, 큐 삽입
						map[nx][ny] = 0;
						q.add(new Point(nx, ny));
						cnt++;
					}
				}
				// 각 단지 내 집의 수 저장
				result.add(cnt);
			}
		}
		
		// 집 수 오름차순 정렬
		Collections.sort(result);
		
		// 결과 출력
		System.out.println(result.size());
		for(int res: result) {
			System.out.println(res);
		}
		

	}

}
