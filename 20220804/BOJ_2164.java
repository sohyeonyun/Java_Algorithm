import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 2164 카드2 - 큐

public class BOJ_2164 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 큐에 1~N 번 카드를 추가한다.
		Queue q = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			q.add(i);
		}
		
		// 카드가 한 장 남을 때까지 동작을 반복 시행한다.
		while(q.size() > 1) {
			q.poll();
			q.add(q.poll());
		}
		
		// 마지막 남은 카드를 보여준다.
		System.out.println(q.poll());
		
	}

}
