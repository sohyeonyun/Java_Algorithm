import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 탈출
 * 
 * 고슴도치S -> 비버D 로 탈출해야한다.
 * 물(*)은 매분마다 빈칸(.)으로 확장한다.
 * 물(*)과 고슴도치S는 돌(X)을 통과할 수 없다.
 */

public class BOJ_3055 {

	static class Pos {
		int x, y, time;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static int r, c;
	static char[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Pos go, be = null;
	static Queue<Pos> waterQ = new LinkedList<>();
	static Queue<Pos> goQ = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'S') {
					goQ.add(new Pos(i, j, 0));
				} else if (map[i][j] == '*') {
					waterQ.add(new Pos(i, j, 0));
				}
			}
		}

		int result = bfs();
		System.out.println(result == -1 ? "KAKTUS" : result);

	}

	private static int bfs() {

		int time = 0;

		// 물이 있는 경우
		while (!waterQ.isEmpty() || !goQ.isEmpty()) {
			// 물 이동 시킴.
			waterBfs(time);
			// 고슴도치 이동시킴.
			if (goBfs(time)) {
				return time + 1;
			}

			time++;
		}
		
		// 비버굴에 도달 못한 경우
		return -1;
	}

	static boolean goBfs(int time) {
		// 고슴도치 이동한다.
		while (!goQ.isEmpty()) {
			if (time != goQ.peek().time) {
				break;
			}
			go = goQ.poll();

			// 사방위
			for (int j = 0; j < 4; j++) {
				int nx = go.x + dx[j];
				int ny = go.y + dy[j];
				// 맵 벗어나면 패스
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
					continue;
				}
				// 돌이나 물이나 이미 방문했으면 패스
				if (map[nx][ny] == 'X' || map[nx][ny] == '*' || map[nx][ny] == 'S') {
					continue;
				}
				// 고슴도치가 굴에 도달하면 종료
				if (map[nx][ny] == 'D') {
					return true;
				}
				// 고슴도치 방문 체크
				map[nx][ny] = 'S';
				goQ.offer(new Pos(nx, ny, go.time + 1));
			}
		}
		return false;
	}

	static void waterBfs(int time) {
		// 물 퍼뜨린다.
		while (!waterQ.isEmpty()) {
			// 시간 지났으면 멈춤
			if (time != waterQ.peek().time) {
				break;
			}
			// 현재 물 위치
			Pos water = waterQ.poll();

			// 사방위
			for (int j = 0; j < 4; j++) {
				int nx = water.x + dx[j];
				int ny = water.y + dy[j];
				// 맵 벗어나면 패스
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
					continue;
				}
				// 돌, 비버나 이미 물이면 패스
				if (map[nx][ny] == 'X' || map[nx][ny] == 'D' || map[nx][ny] == '*') {
					continue;
				}
				// 물 퍼뜨림
				map[nx][ny] = '*';
				waterQ.offer(new Pos(nx, ny, water.time + 1));
			}
		}
	}

}
