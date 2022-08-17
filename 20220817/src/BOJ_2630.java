import java.util.Scanner;

public class BOJ_2630 {
	static int[][] map;
	
	// 색종이 개수
	static int white = 0;
	static int blue = 0;

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// dfs
		dfs(0, 0, n);
		
		System.out.println(white);
		System.out.println(blue);
	}
	
	static void dfs(int x, int y, int n) {
		
		// 해당 칸에서 모두 같은 색이면 종료
		int flag = map[x][y];
		boolean same = true;
		LOOP: for(int i = x; i< x + n; i++) {
			for(int j = y; j< y + n; j++) {
				if(flag != map[i][j]) {
					same = false;
					break LOOP;
				}
			}
		}
		
		if(same) {
			if(flag == 1) blue++;
			else white++;
		} else {
			dfs(x, y, n/2);
			dfs(x, y + n/2, n/2);
			dfs(x + n/2, y, n/2);
			dfs(x + n/2, y + n/2, n/2);
		}
		
	}

}
