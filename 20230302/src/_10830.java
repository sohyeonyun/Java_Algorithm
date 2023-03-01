import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10830 {
	
	static int N;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		
		// 계산
		int[][] result = cal(map, k);
		
		// 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 행렬, 지수
	private static int[][] cal(int[][] matrix, long k) {

		// 재귀 종료 조건
		if(k == 1) {
			return matrix;
		}
		
		// k 제곱을 절반으로 줄여나간다.
		int[][] tmp = cal(matrix, k/2);
		
		// k 지수가 홀수면 마지막에 한 번 더 곱해준다.
		if(k % 2 == 1) {
			return square(square(tmp, tmp), matrix);
		} else {
			return square(tmp, tmp);
		}
	}
	
	// 행렬A * 행렬B
	private static int[][] square(int[][] A, int[][] B) {
		int[][] result = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 행렬 곱
				for(int k=0; k<N; k++) {
					result[i][j] += A[i][k] * B[k][j];
					result[i][j] %= 1000;
				}
			}
		}
		
		return result;
	}

}
