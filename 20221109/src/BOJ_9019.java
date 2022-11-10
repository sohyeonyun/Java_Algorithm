import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * DSLR
 */
public class BOJ_9019 {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0; t<T; t++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			System.out.println(bfs(A, B));
		}
	}

	private static String bfs(int A, int B) {
		
		Queue<Data> q = new LinkedList<>();
		q.offer(new Data(A, ""));
		boolean[] v = new boolean[10000];
		v[A] = true;
		
		int a;
		int r, l, s, d;
		while(!q.isEmpty()) {
			Data cur = q.poll();
			a = cur.num;
			
			// A, B 일치하면 리턴 
			if(a == B) {
				return cur.path;
			}
			
			r = operR(a);
			l = operL(a);
			s = operS(a);
			d = operD(a);
			if(!v[r]) {
				v[r] = true;
				q.offer(new Data(r, cur.path + "R"));
			}
			if(!v[l]) {
				v[l] = true;
				q.offer(new Data(l, cur.path + "L"));
			}
			if(!v[s]) {
				v[s] = true;
				q.offer(new Data(s, cur.path + "S"));
			}
			if(!v[d]) {
				v[d] = true;
				q.offer(new Data(d, cur.path + "D"));
			}
		}
		
		return "";
	}

	private static int operR(int a) {
		return (a % 10) * 1000 + (a / 10);
	}

	private static int operL(int a) {
		return (a % 1000) * 10 + a / 1000;
	}

	private static int operS(int a) {
		return (a - 1 + 10000) % 10000;
	}

	private static int operD(int a) {
		return (a * 2) % 10000;
	}
	
	static class Data {
		int num;
		String path;
		
		public Data(int num, String path) {
			super();
			this.num = num;
			this.path = path;
		}
		
	}
	
	

}
