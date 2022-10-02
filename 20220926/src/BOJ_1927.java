import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 최소 힙 
 */

public class BOJ_1927 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int k = Integer.parseInt(br.readLine());
			if(k == 0) {
				sb.append(minHeap.isEmpty() ? 0 : minHeap.poll()).append("\n");
			} else {
				minHeap.add(k);
			}
			
		}
		System.out.println(sb);
				
	}

}
