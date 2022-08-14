import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 4963 섬의 개수
 */

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_4963 {

	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 상하좌우, 대각선 탐색
		int [] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
		int [] dy = {0, 0, -1, 1, 1, 1, -1, -1};
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			// 입력 종료
			if(w == 0 && h == 0) {
				break;
			}
			
			// 섬의 위치 기록
			List<Position> lands = new ArrayList<>();
			
			// 맵 입력받기
			int [][] map = new int[h][w];
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 섬의 위치를 큐에 삽입
					if(map[i][j] == 1) {
						lands.add(new Position(i, j));
					}
				}
			}
			
			// 섬의 개수
			int count = 0;
			
			// BFS 위해 Queue 생성
			Queue<Position> q = new LinkedList<>();
			
			// BFS
			for(Position land: lands) {
				// 이미 방문한 경우 패스
				if(map[land.x][land.y] == 0) continue;
				// 큐에 탐색 시작할 위치 삽입
				q.add(land);
				
				// 현재 섬에 대해 이웃한 섬을 찾는다
				while(!q.isEmpty()) {
					Position cur = q.poll();
					int x = cur.x;
					int y = cur.y;
					// 방문 표시
					map[x][y] = 0;
					// 인접 구역 탐색 (8방위)
					for(int i=0; i<8; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						// 맵 벗어나거나 바다(0)인 경우 패스
						if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
						if(map[nx][ny] == 0) continue;
						// 땅(1)이면 방문 표시 후, 다시 큐에 삽입
						map[nx][ny] = 0;
						q.add(new Position(nx, ny));
					}
				}
				// 섬의 개수 갱신
				count++;
			}
			
			System.out.println(count);
		}
		
	}

}
