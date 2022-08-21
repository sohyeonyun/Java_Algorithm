import java.util.Scanner;

/*
 * 일우는 야바위꾼 - 브3 
 */
public class BOJ_20361 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int X = sc.nextInt();
		int K = sc.nextInt();
		
		// 명령어 K개 입력받기 
		for(int i=0; i<K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(a == X) X = b;
			else if(b == X) X = a;
		}
		
		System.out.println(X);
				
	}

}
