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
		
		boolean[] usedCraine = new boolean[N];
		
		for(int i=0; i<M; i++) {
			for(int j=i; j<M; j++) {
				System.out.println(i + " " + j + ": " + craines[craine_idx] + " " + boxes[j]);
				if (boxes[j] == -1) {
					System.out.println(1);
					break;
				}
				if (usedCraine[craine_idx]) {
					craine_idx = (craine_idx + 1) % N;
					System.out.println(2);
					System.out.println(2);
					if(craine_idx == N - 1) {
						answer++;
						Arrays.fill(usedCraine, false);
					}
				}
				if (craines[craine_idx] >= boxes[j]) {
					System.out.println(3);
					boxes[j] = -1;
					usedCraine[craine_idx] = true;
					if(craine_idx == N - 1) {
						answer++;
						Arrays.fill(usedCraine, false);
					}
					craine_idx = (craine_idx + 1) % N;
				} else if(craine_idx == 0){
					System.out.println(-1);
					return;
				} else {
					System.out.println(4);
					
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
