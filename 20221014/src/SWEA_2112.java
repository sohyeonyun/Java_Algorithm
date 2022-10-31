import java.util.Arrays;
import java.util.Scanner;

public class SWEA_2112 {
	static int res;
	static int D; // 보호필름 두께
	static int W; // 보호필름 넒이
	static int K; // 합격기준
	static int[][] map; // 보호필름

	static int[] list; // 보호필름의 약물 변경하기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();
		for (int t = 1; t <= TC; t++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			res = K; // 최대사용하는 경우
			map = new int[D][W]; // 보호필름 생성하기
			// 보호필름 정보를 얻어오기
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt(); // 0이면 A 1이면 B
				}
			}
//          보호필름 변경할 배열 D 만큼 선언 및 초기화
			list = new int[D];
			Arrays.fill(list, -1); // -1 임의이값으로 변경안함
			// 솔루션 구현
			dfs(0, 0); // 변경할 행, 갯수

			System.out.println("#" + t + " " + res);
		}
	}

	// 파워셋 구현 두께만큼 구해보기
	private static void dfs(int idx, int cnt) {
//      종료 조건
		if (idx == D) { // 두깨만큼 모든부분집합 구해지면
			if (isCheck()) { // 보호필름이 보호가 되면 최소값 구해진 것과 비교하기
				res = Math.min(res, cnt);
			}
			return;
		}
		if (cnt >= res) { // 이미 구해진 최소값보다 보호필름 변겨이 넘었다면 가지치기
			return;
		}
		list[idx] = -1; // 현재셀 변경안함
		dfs(idx + 1, cnt); // 보호필름 사용 안함
		list[idx] = 0; // 현재셀 A로 변경함
		dfs(idx + 1, cnt + 1); // 보호필름 1장 사용함
		list[idx] = 1; // 현재셀 B로 변경함
		dfs(idx + 1, cnt + 1); // 보호필름 1장 사용함
	}

	private static boolean isCheck() {
		// TODO Auto-generated method stub
//      보호필름의 열우선으로 검사하기
		int cnt = 0; // 연속된 셀 값 세기
		int a, b; // 현재셀값,다음셀값
		for (int j = 0; j < W; j++) {
//          모든 열마다 초기화 필요 최소 길이는 1장이다.
			cnt = 1;
			for (int i = 0; i < D - 1; i++) { // 서로 비교하는값임으로 D-1까지만 반복
				a = map[i][j];
				b = map[i + 1][j];
//              현재셀을 변경한 경우라면 값 변경하기
				if (list[i] != -1) {
					a = list[i];
				}
//              다음셀 값 변경된 경우라면 값 변경하기
				if (list[i + 1] != -1) {
					b = list[i + 1];
				}
//              연속된 두 셀의 값이 같으면 계속 다르면 다시 1부터 계산하기
				if (a == b) {
//                  보호필름 갯수 세기
					cnt++;
					if (cnt >= K) { // 보호기능이 가능하면 더이상 안해보고 안쪽 반복문 벗어나기
						break;
					}
				} else {// 다르면 다시 1부터 시작하기
					cnt = 1;
				}
			}
//          안쪽 반복문을 벗어났는데 이 열름 보호되지 않으면 바로 false 반환
			if (cnt < K) {
				return false;
			}
		}
//      기본적으로 보호필름 사용 가능함
		return true;
	}

}