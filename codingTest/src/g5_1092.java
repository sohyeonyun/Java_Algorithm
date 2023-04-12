import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class g5_1092 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Integer[] craines = new Integer[N];
		for (int i = 0; i < N; i++) {
			craines[i] = sc.nextInt();
		}
		
		int M = sc.nextInt();
		Integer[] boxes = new Integer[M];
		for (int i = 0; i < M; i++) {
			boxes[i] = sc.nextInt();
		}

		// 내림차순 정렬
		Arrays.sort(craines, Collections.reverseOrder());
		Arrays.sort(boxes, Collections.reverseOrder());
		
		int answer = 1;
		int craine_idx = 0;
		int box_idx = 0;
		while(box_idx < M) {
			int box = boxes[box_idx];
			
			// 크레인 하나씩 탐색
			for(int j=0; j<M; j++) {
				int cur = (craine_idx + j) % M;
				
				// 담기
				if(craines[cur] >= box) {
					// 마지막 크레인
					if(craine_idx == N - 1) {
						answer++;
					}
					// 다음 크레인
					craine_idx = (craine_idx + 1) % N;
					box_idx++;
					break;
				}
				// 못 담아
				else {
					// 첫번째 크레인인데 못담으면 다 못담음!!
					if(craine_idx == 0) {
						System.out.println(-1);
						return;
					}
					// 크레인 한번 옮기고 젤 앞 크레인으로 포인팅
					answer++;
					j = M - 1; // 마지막 회차로 시킴.
					craine_idx = 0;
				}
				
			}
		}
		
		System.out.println(answer);
		
	}

}
