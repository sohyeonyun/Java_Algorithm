import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV {
	int num;
	int x, y;
	
	public CCTV(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15683 {
	static int n, m;
	static int[][] map, copyMap;
	static ArrayList<CCTV> cctvList = new ArrayList<>();
	static int[] output;
	static int[] dx = {-1, 0, 1, 0}; // 상우하좌
	static int[] dy = {0, 1, 0, -1};
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new CCTV(map[i][j], i, j));
				}
			}
		}
		
		output = new int[cctvList.size()]; // 순열 결과 담을 리스트
		permutation(0, cctvList.size());
		
		System.out.println(answer);
	}
	
	// cctv의 방향(상우하좌)중에서 cctv 총 개수(t개) 만큼 뽑는다.
	static void permutation(int cnt, int t) {
		if(cnt == t) {
			// 원본 지도 복사
			copyMap = new int[n][m];
			for(int i=0; i<n; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
			}
			
			// cctv 번호와 순열로 뽑힌 방향에 맞는 상하좌우 방향 설정 
			for(int i=0; i<t; i++) {
				direction(cctvList.get(i), output[i]);
			}
			
			// 사각지대 개수 구하기
			getBlindSpot();
			return;
		}
		
		for(int i=0; i<4; i++) {
			output[cnt] = i;
			permutation(cnt + 1, t);
		}
	}

	// cctv 번호에 맞게 방향 설정
	static void direction(CCTV cctv, int d) {
		int cctvNum = cctv.num;
		
		if(cctvNum == 1) {
			if(d == 0) watch(cctv, 0);
			else if(d == 1) watch(cctv, 1);
			else if(d == 2) watch(cctv, 2);
			else if(d == 3) watch(cctv, 3);
		} else if (cctvNum == 2) {
			if(d == 0 || d == 2) {
				watch(cctv, 0);
				watch(cctv, 2);
			} else {
				watch(cctv, 1);
				watch(cctv, 3);
			}
		} else if(cctvNum == 3) {
			if(d == 0) {
				watch(cctv, 0);
				watch(cctv, 1);
			} else if(d == 1) {
				watch(cctv, 1);
				watch(cctv, 2);
			} else if(d == 2) {
				watch(cctv, 2);
				watch(cctv, 3);
			} else if(d == 3) {
				watch(cctv, 3);
				watch(cctv, 0);
			}
		} else if(cctvNum == 4) {
			if(d == 0) {
				watch(cctv, 0);
				watch(cctv, 1);
				watch(cctv, 2);
			} else if(d == 1) {
				watch(cctv, 1);
				watch(cctv, 2);
				watch(cctv, 3);
			} else if(d == 2) {
				watch(cctv, 2);
				watch(cctv, 3);
				watch(cctv, 0);
			} else if(d == 3) {
				watch(cctv, 3);
				watch(cctv, 0);
				watch(cctv, 1);
			}
		} else {
			watch(cctv, 0);
			watch(cctv, 1);
			watch(cctv, 2);
			watch(cctv, 3);
		}
	}
	
	// cctv가 해당 방향을 감시함.
	static void watch(CCTV cctv, int d) {
		int x = cctv.x;
		int y = cctv.y;
		
		while(true) {
			x += dx[d];
			y += dy[d];
			
			// 범위 벗어나거나 벽 만나면 끝
			if(x < 0 || x >= n || y < 0 || y >= m || copyMap[x][y] == 6) {
				break;
			}
			
			// 빈 칸이면 감시
			if(copyMap[x][y] == 0) {
				copyMap[x][y] = -1; // 감시 표시
			}
			
		}
	}
	
	static void getBlindSpot() {
		int sum = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(copyMap[i][j] == 0) {
					sum++;
				}
			}
		}
		answer = Math.min(sum, answer);
	}

}



