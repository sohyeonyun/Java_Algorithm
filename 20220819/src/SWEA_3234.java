import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * SWEA 3234 - 준환이의 양팔저울
 */
public class SWEA_3234 {
	
	static int n;
	static int[] weights;
	static int[] order;
	static boolean[] selected, visited;
	static int totalCnt;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			// 초기화 ㅋ
			totalCnt = 0;
			
			// 입력 받기
			n = sc.nextInt();
			weights = new int[n];
			for(int i=0; i<n; i++) {
				weights[i] = sc.nextInt();
			}
			
			// n! 구하기
			order = new int[n];
			selected = new boolean[n];
			factorial(0);
			
			// 결과 출력
			System.out.printf("#%d %d%n", t, totalCnt);
		}
	}
	
	static void factorial(int cnt) {
		// n개를 줄 세웠으면 어느 저울에 올릴지 결정하기
		if(cnt == n) {
			visited = new boolean[n];
			subset(0, 0, 0);
			return;
		}
		
		for(int i=0; i<n; i++) {
			// 방문했으면 패스
			if(selected[i]) continue;
			// 방문 표시하고 해당 무게 추 선택
			selected[i] = true;
			order[cnt] = weights[i];
			// 다음 추 줄세우러 가기
			factorial(cnt + 1);
			// 사용했던 추 되돌리기
			selected[i] = false;
		}
	}
	
	// 왼쪽 추의 합과 오른쪽 추의 합을 매개변수로 넘겨서 조건에 맞지 않으면 탐색하지 않는다.
	static void subset(int cnt, int leftSum, int RightSum) {
		// 오른쪽 저울의 무게 총합이 더 크면 가지치기
		if(leftSum < RightSum) return;
		
		// 조건에 벗어나지 않고 n개를 다 선택하면 합계 늘리고 종료
		if(cnt == n) {
			totalCnt++;
			return;
		}
		
		// true면 오른쪽 저울로 올린다.
		visited[cnt] = true;
		subset(cnt + 1, leftSum, RightSum + order[cnt]);
		// false면 왼쪽 저울로 올린다.
		visited[cnt] = false;
		subset(cnt + 1, leftSum + order[cnt], RightSum);
	}

}





