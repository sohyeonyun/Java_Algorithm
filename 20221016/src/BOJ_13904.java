import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13904 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 과제 마감일 1<=d<=1000
		ArrayList<Integer>[] dday = new ArrayList[1001];
		
		// 각 마감 날짜별로 점수 입력받기
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if(dday[d] == null) {
				dday[d] = new ArrayList<>();
			}
			dday[d].add(w);
		}
		
		// 과제 점수 내림차순으로 삽입할 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		int sum = 0;
		for(int i=1000; i>=1; i--) {
			if(dday[i] != null) {
				// 거꾸로 탐색하기 때문에 마감일 수가 큰 과제들은 마감일 수가 작은날에 수행할 수 있다.
				pq.addAll(dday[i]);
			}
			if(!pq.isEmpty()) {
				// 점수 가장 큰 과제 수행
				sum += pq.poll();
			}
		}
		
		System.out.println(sum);
	}

}
