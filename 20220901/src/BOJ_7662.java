import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 이중 우선순위 큐
 */

public class BOJ_7662 {
	
	static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {

			// 전체 수의 개수를 관리할 맵
			map = new HashMap<>();
			// 최소, 최대 우선순위 큐
			PriorityQueue<Integer> minPq = new PriorityQueue<>();
			PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

			int N = Integer.parseInt(br.readLine());
			char option;
			int num;

			for (int i = 0; i < N; i++) {
				
				// 입력
				st = new StringTokenizer(br.readLine());
				option = st.nextToken().charAt(0);
				num = Integer.parseInt(st.nextToken());

				switch (option) {
				case 'I': // 삽입
					map.put(num, map.getOrDefault(num, 0) + 1);

					minPq.offer(num);
					maxPq.offer(num);
					break;
				case 'D': // 삭제
					if (map.isEmpty()) { // 맵이 비었으면 아무 수도 없는거
						continue;
					}

					if(num == 1) {
						remove(maxPq);
					} else {
						remove(minPq);
					}
					
				}
			}

			if (map.isEmpty()) {
				sb.append("EMPTY").append("\n");
			} else {
				int res = remove(maxPq);
				sb.append(res).append(" ");
				if(!map.isEmpty()) res = remove(minPq); // 숫자가 하나 남을수 있다.
				sb.append(res).append("\n");
			}
		}

		System.out.print(sb);
	}
	
	static int remove(PriorityQueue<Integer> pq) {
		int k;
		
		// 최대 or 최솟값 꺼내서 맵에 있다면 유효한 값
		while(true) {
			k = pq.poll();
			
			// 다른 큐에서 꺼내서 맵에 없을 수 있다..
			int cnt = map.getOrDefault(k, 0);
			if(cnt == 0) continue;
			
			// 삭제 후 0개면 맵에서 삭제해줘야함.
			if(cnt == 1) map.remove(k);
			else map.replace(k, cnt - 1);
			break;
		}
		
		return k;
	}

}
