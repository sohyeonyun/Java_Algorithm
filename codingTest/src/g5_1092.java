import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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

		Arrays.sort(craines, Collections.reverseOrder());
		Arrays.sort(boxes, Collections.reverseOrder());
		System.out.println(Arrays.toString(craines));
		System.out.println(Arrays.toString(boxes));
		
		int answer = 1;
		int craine_idx = 0;
		int box_idx = 0;
		while(box_idx < M) {
			int box = boxes[box_idx];
			System.out.println(box_idx + " " + box + "!!!!!!");
			
			// 크레인 하나씩 탐색
			for(int j=0; j<M; j++) {
				int idx = (craine_idx + j) % M;
				System.out.println("박스: " + box_idx + " " + box + " 크레인: " + craine_idx + " " + craines[craine_idx]);
				// 담기
				if(craines[idx] >= box) {
					// 마지막 크레인
					if(craine_idx == N - 1) {
						answer++;
					}
					// 다음 크레인
					System.out.println(craine_idx);
					craine_idx = (craine_idx + 1) % N;
					System.out.println(craine_idx);
					box_idx++;
					break;
				}
				// 못 담아
				else {
					// 첫번째 크레인
					if(craine_idx == 0) {
						System.out.println(-1);
						return;
					}
					// 마지막 크레인
					if(craine_idx == N - 1) {
						answer++;
						box_idx = (box_idx + 1) % M;
						craine_idx = 0;
						break;
					}
					craine_idx++;
					box_idx = (box_idx + 1) % M;
				}
				
			}
			
			System.out.println(box_idx);
			System.out.println();
		}
		
		System.out.println(answer);
		
	}

}
