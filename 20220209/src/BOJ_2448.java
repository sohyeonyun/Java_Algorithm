import java.util.Arrays;
import java.util.Scanner;

/*
 * 별 찍기 11 
 */
public class BOJ_2448 {
	
	static char[][] arr;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		arr = new char[N][2 * N - 1];
		
		// 초기화 
		for(int i=0; i<N; i++) {
			Arrays.fill(arr[i], ' ');
		}
		
		// 재귀 
		triangle(N, 0, N - 1);
		
		// 출력 
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j< 2 * N - 1; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}
	
	// 길이 + 삼각형 꼭짓점 (r, c)
	private static void triangle(int len, int r, int c) {
		if(len == 3) {
			draw(r, c);
			return;
		}
		
		// 위쪽, 왼쪽, 오른쪽 삼각형 
		triangle(len / 2, r , c);
		triangle(len / 2, r + len / 2, c - len / 2);
		triangle(len / 2, r + len / 2, c + len / 2);
	}

	// r x c 가 윗꼭짓점인 높이 3인 삼각형 그리기 
	static void draw(int r, int c) {
		// 첫번째 줄 
		arr[r][c] = '*';
	
		// 두번째 줄 
		arr[r + 1][c - 1] = '*';
		arr[r + 1][c + 1] = '*';
		
		// 세번째 줄 
		for(int i=0; i<5; i++) {
			arr[r + 2][c + i - 2] = '*';		
		}
		
	}

}
