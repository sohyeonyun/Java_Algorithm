import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20299 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 모든 팀원의 레이팅이 L 이상이고 팀원 세명의 레이팅 합이 K 이상이면 가입 가능  
			if(a >= L && b >= L && c >= L && (a + b + c) >= K) {
				cnt++;
				sb.append(a).append(" ");
				sb.append(b).append(" ");
				sb.append(c).append(" ");
			}
			
		}
		
		System.out.println(cnt);
		System.out.println(sb);
	}

}
