import java.io.*;
import java.util.*;

public class g2_17780 {

	static int N, K; // 판크기, 말개수
	static int[][] map; // 0흰, 1빨, 2파
	static ArrayList<Integer>[][] playerMap; // 위치대로 말
	static Player[] players; // 순서대로 말
	static int[] dr = {0, 0, 0, -1, 1}; // 우좌위아  1<->2, 3<->4
	static int[] dc = {0, 1, -1, 0, 0};
	static int turn = 0;
	
	public static void main(String[] args) throws Exception {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		playerMap = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				playerMap[i][j] = new ArrayList<>();
			}
		}
		
		players = new Player[K]; // 0 ~ k-1 번 말
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 0 ~ N-1
			int c = Integer.parseInt(st.nextToken()) - 1; // 0 ~ N-1
			int dir = Integer.parseInt(st.nextToken()); // 1, 2, 3, 4
			players[i] = new Player(r, c, dir); // player list
			playerMap[r][c].add(i); // (r, c) 위치에 i번 player
		}
		
		// logic
		game();
		
		// output
		if(turn >= 1000) {
			turn = -1;
		}
		System.out.println(turn);
	}
	
	public static void game() {
		
		while(turn++ < 10) {
			
			// 말 하나씩 이동
			for(int i=0; i<K; i++) {
				Player p = players[i]; // r, c, dir
				System.out.println("!!!!!!!!!!!!!" + p);
				
				System.out.println(i + " " + playerMap[p.r][p.c]);
				// 맨 밑의 말이 아니면 못 움직이니 패스
				if(i != playerMap[p.r][p.c].get(0)) {
					continue;
				}
				
				int nr = p.r + dr[p.dir];
				int nc = p.c + dc[p.dir];
				System.out.println("[" + p.r + " " + p.c + "] --> " + nr + " " + nc);
				// 다음 이동 칸이 말판 튀어나가면, 방향 전환
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) { 
					System.out.println("[" + p.r + " " + p.c + "] --> " + nr + " " + nc + " : " + p.dir);
					p.dir = changeDir(p.dir);
					nr = p.r + dr[p.dir];
					nc = p.c + dc[p.dir];
					System.out.println("[" + p.r + " " + p.c + "] --> " + nr + " " + nc + " : " + p.dir);
				}
				// 흰 색 (0)이면 그냥 이동
				if(map[nr][nc] == 0) {
					System.out.println("0000000000000");
					int originR = p.r;
					int originC = p.c;
					for(Integer cur: playerMap[p.r][p.c]) {
						players[cur].r = nr;
						players[cur].c = nc;
						playerMap[nr][nc].add(cur);
					}
					playerMap[originR][originC].clear();
					System.out.println(playerMap[originR][originC] + " ===> " + playerMap[nr][nc]);
				} else if(map[nr][nc] == 1) { // 빨간색(1)
					System.out.println("11111111111");
					int originR = p.r;
					int originC = p.c;
					for(Integer cur: playerMap[p.r][p.c]) {
						players[cur].r = nr;
						players[cur].c = nc;
						playerMap[nr][nc].add(cur);
					}
					playerMap[originR][originC].clear();
					Collections.reverse(playerMap[nr][nc]);
					System.out.println(playerMap[originR][originC] + " ===> " + playerMap[nr][nc]);
					
				} else { // 파란색(2)
					System.out.println("22222222222");
					System.out.println(playerMap[p.r][p.c] + " ===> " + playerMap[nr][nc]);
				}
				
				System.out.println();
				
			}
			
		}
		
	}
	
	public static int changeDir(int dir) {
		// 1<->2, 3<->4
		if(dir == 1) {
			return 2;
		} else if(dir == 2) {
			return 1;
		} else if(dir == 3) {
			return 4;
		} else {
			return 3;
		}
	}
	
	static class Player {
		int r, c, dir;
		
		public Player (int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Player [r=" + r + ", c=" + c + ", dir=" + dir + "]";
		}
	}

}
