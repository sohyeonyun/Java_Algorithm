import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5644 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 총 이동 시간
			int A = Integer.parseInt(st.nextToken()); // BC 개수
			
			int[] moveA = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] moveB = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			
			
		}
	}

}
