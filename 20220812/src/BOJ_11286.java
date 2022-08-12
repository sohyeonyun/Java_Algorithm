import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 절댓값 힙
 */

class Num {
	int x;
	int absX;
	Num(int x, int absX) {
		this.x = x;
		this.absX = absX;
	}
}

public class BOJ_11286 {

	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Num> pq = new PriorityQueue<>(new Comparator<Num>() {
			@Override
			public int compare(Num o1, Num o2) {
				if(o1.absX == o2.absX) {
					return Integer.compare(o1.x, o2.x);
				}
				return Integer.compare(o1.absX, o2.absX);
			}
		});
		
		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0 ) {
				// 힙에 추가
				pq.offer(new Num(x, Math.abs(x)));
			} else {
				// 비어 있으면 0 출력
				if(pq.isEmpty()) {
					bw.write(0 + "\n");
				} else {
					// 절댓값 가장 작은 수 출력
					bw.write(pq.poll().x + "\n");
				}
			}
		}
		bw.flush();
		
	}

}
