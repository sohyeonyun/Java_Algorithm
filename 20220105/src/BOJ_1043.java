import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 거짓말 
 */
public class BOJ_1043 {
	
	static int[] parent;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// union-find 초기화 
		parent = new int[N + 1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		// 진실 아는지 
		boolean[] know = new boolean[N + 1];
		
		// 진실 아는 사람 
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		for(int i=0; i<K; i++) {
			int t = Integer.parseInt(st.nextToken());
			know[t] = true; 
		}
		
		// 파티 멤버 저장할 곳 
		ArrayList<Integer>[] members = new ArrayList[M];
		for(int i=0; i<M; i++) {
			members[i] = new ArrayList<>();
		}
		
		// 파뤼 타임 - 같은 파티면 union
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// k명 파티 멤버 입력받기 
			int k = Integer.parseInt(st.nextToken());
			// 0번째 멤버 
			int pre = Integer.parseInt(st.nextToken());
			members[i].add(pre);
			// 1번째 ~ (k-1)번째 멤버 
			for(int j=1; j<k; j++) {
				int member = Integer.parseInt(st.nextToken());
				members[i].add(member);
				union(pre, member);
				pre = member;
			}
		}
		
		// 거짓말 횟수 
		int answer = 0;
		
		// 전체 멤버 확인하며 진실 아는 사람의 부모도 진실 알게 됨을 체크 
		for(int i=1; i<=N; i++) {
			if(know[i]) {
				know[parent[find(i)]] = true;
			}
		}
		
		// 전체 파티 체크하며 진실 아는 사람 없는 파티만 거짓말 
		for(int i=0; i<M; i++) {
			int p = find(members[i].get(0)); // 파티 방장
			if(!know[p]) answer++;
		}
		
		// 정답 
		System.out.println(answer);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) {
			return;
		}
		parent[b] = a;
	}
	
	private static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
