import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 특이한 자석 
 */
public class SWEA_4013 {
	
	static Magnet[] magnets;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			magnets = new Magnet[4];
			
			int K = Integer.parseInt(br.readLine());
			
			// 자석 정보 
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				magnets[i] = new Magnet(new int[8], 0, -1);
				// 8방향 
				for(int j=0; j<8; j++) {
					magnets[i].arr[j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// K 번 회전 
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken()) - 1;
				int direction = Integer.parseInt(st.nextToken());
				
				simulate(index, direction);
			}
			
			int ans = getPoint();
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static int getPoint() {
		int[] points = {1, 2, 4, 8};
		int point = 0;
		
		for(int i=0; i<4; i++) {
			// S극이면 점수 얻기 
			if(magnets[i].arr[magnets[i].start] == 1) {
				point += points[i];
			}
		}
		
		return point;
	}

	private static void simulate(int index, int direction) {
		
		// 자석 돌릴지 여부
		boolean[] turn = new boolean[4];
		// 현재 자석 돌리기
		turn[index] = true;
		
		// 돌리는 자석 기준 왼쪽
		int cur_idx = index - 1;
		Magnet left, right;
		while(cur_idx >= 0) {
			left = magnets[cur_idx];
			right = magnets[cur_idx + 1];
			
			// 자석 반대 방향이면 회전 
			if(left.arr[(left.start + 2) % 8] != right.arr[(right.start - 2 + 8) % 8]) {
				turn[cur_idx] = true;
			} else {
				break;
			}
			
			cur_idx--;
		}
		
		// 돌리는 자석 기준 오른쪽 
		cur_idx = index + 1;
		while(cur_idx < 4) {
			left = magnets[cur_idx - 1];
			right = magnets[cur_idx];
			
			// 자석 반대 방향이면 회전 
			if(left.arr[(left.start + 2) % 8] != right.arr[(right.start - 2 + 8) % 8]) {
				turn[cur_idx] = true;
			} else {
				break;
			}
			
			cur_idx++;
		}
		
		// 자석 회전
		for(int i=0; i<4; i++) {
			if(i % 2 == index % 2) {
				magnets[i].dir = direction;
			} else {
				magnets[i].dir = direction * (-1);
			}
		}
		
		for(int i=0; i<4; i++) {
			// 돌리지 않으면 패스  
			if(!turn[i]) continue;
			// 시계 방향 
			if(magnets[i].dir == 1) {
				magnets[i].start = (magnets[i].start - 1 + 8) % 8;
			} else { // 반시계 
				magnets[i].start = (magnets[i].start + 1 + 8) % 8;
			}
		}
		
	}

	static class Magnet {
		int[] arr;
		int start, dir;
		
		public Magnet(int[] arr, int start, int dir) {
			super();
			this.arr = arr;
			this.start = start;
			this.dir = dir;
		}
		
	}

}
