import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 숨바꼭질 3
 */
public class BOJ_13549 {
	
	static int MAX = 100001;
	static boolean[] visited = new boolean[MAX]; 

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		
		Queue<Data> q = new LinkedList<>();
		q.offer(new Data(N, 0));
		visited[N] = true;
		
		Data data = null;
		while(!q.isEmpty()) {
			data = q.poll();
			if(data.point == K) {
				break;
			}
			
			if(isValid(data.point * 2)) {
				q.offer(new Data(data.point * 2, data.time));
				visited[data.point * 2] = true;
			}
			if(isValid(data.point - 1)) {
				q.offer(new Data(data.point - 1, data.time + 1));
				visited[data.point - 1] = true;
			}
			if(isValid(data.point + 1)) {
				q.offer(new Data(data.point + 1, data.time + 1));
				visited[data.point + 1] = true;
			}
		}
		
		System.out.println(data.time);
		
	}
	
	private static boolean isValid(int point) {
		return 0 <= point && point < MAX && !visited[point];
	}
	
	static class Data {
		int point, time;

		public Data(int point, int time) {
			super();
			this.point = point;
			this.time = time;
		}
	}

}
