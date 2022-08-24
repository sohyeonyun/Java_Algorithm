import java.util.Scanner;

public class PrimTest {
	// O(V logV + E)

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int res = 0;

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 선택된 정점 관리 배열
		boolean[] v = new boolean[N];
		// 현재 정점이 포함되면 발생하는 최소 비용 관리 테이블
		int[] D = new int[N];
		// 1. 모든 정점까지의 비용을 최댓값으로 초기화 한다.
		for (int i = 0; i < N; i++) {
			D[i] = Integer.MAX_VALUE;
		}
		// 2. 임의의 정점을 시작 정점으로 설정하여, D 배열의 시작 비용을 0으로 바꾼다.
		D[0] = 0;
		// 3-1. 모든 정점에 대해서 작업을 진행함.
		for (int i = 0; i < N; i++) {
			// 3-2. 현재 D 배열에서 최솟값과 그 위치를 찾는다.
			int min = Integer.MAX_VALUE;
			int minIdx = 0;
			for (int j = 0; j < N; j++) {
				// (MST에 포함되지 않는 정점에서)
				if (v[j])
					continue;
				if (min > D[j]) {
					min = D[j];
					minIdx = j;
				}
			}
			// 3-3. 최소 비용을 최종 결과값에 누적한다.
			// 위에서 선택된 위치값을 MST에 포함시킴.
			res += min;
			v[minIdx] = true;
			// 3-4. 최소 비용으로 선택된 정점에서 연결된 나머지 정점으로의
			// 비용을 최솟값으로 업데이트 한다.
			// (이미 MST에 포함된 정점은 제외하고)
			for (int j = 0; j < N; j++) {
				if (v[j])
					continue;
				// 현재 선택된 위치에서 연결되지 않는 정점을 무시
				if (map[minIdx][j] == 0)
					continue;
				// 현재 선택된 위치에서 연결된 정점에서
				// 기존 D 배열에 있는 비용보다 현재 새롭게 선택된 정점에서 나가는 비용이
				// 저렴하면 D 배열의 비용을 업데이트 한다. -> 그리디
				if (D[j] > map[minIdx][j]) {
					D[j] = map[minIdx][j];
				}
			}
		}

		System.out.println("mst : " + res);

	}

}
