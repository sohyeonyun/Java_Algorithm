import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 스도쿠
 */
public class BOJ_2239 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[9][9];
		for(int i=0; i<9; i++) {
			String s = br.readLine();
			for(int j=0; j<9; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		simulate();
		
		
//		checkSquare(x, y);
//		checkLine(x);
		
	}

	private static void simulate() {


	}

}










