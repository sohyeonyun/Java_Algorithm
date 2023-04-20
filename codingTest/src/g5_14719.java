import java.util.*;
import java.io.*;

public class g5_14719 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 세로, 가로 
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 블록 쌓인 곳 == 1
		int[][] blocks = new int[R][C];
		
		st = new StringTokenizer(br.readLine());
		for(int j=0; j<C; j++) {
			int h = Integer.parseInt(st.nextToken());
			for(int i=0; i<h; i++) {
				blocks[i][j] = 1;
			}
		}
		
		// 계산 
		int answer = 0;
		for(int i=0; i<R; i++) {
			int start = -1, end = 0;
			int sum = 0;
			for(int j=0; j<C; j++) {
				end = j;
				if(blocks[i][j] == 1) { // block
					// 처음 블록이면 start 표시 
					if(start == -1) {
						start = j;
					} else if(sum > 0) { // start ~ end 사이에 빗물 공간 있는지 
						answer += sum;
						sum = 0;
						start = end;
					} // 계속 블록인 경우 
				} else if(start != -1){ // 빗물 공간 
					sum++;
				}
			}
		}
		
		System.out.println(answer);
		
		
	}

}
