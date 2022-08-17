
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class SWEA_6808 {
	static Integer[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
	static Integer[] kyu, inYoung;
	static int[] nums = new int[9];
	static boolean[] isSelected = new boolean[9];
	static int win, lose;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		for(int t=1; t<=T; t++) {
			// 규영이 승패 결과
			win=0; lose=0;
			
			// 규영이의 카드 순서
			String[] s = sc.nextLine().split(" ");
			kyu = new Integer[9];
			for(int i=0; i<9; i++) {
				kyu[i] = Integer.parseInt(s[i]);
			}
			
			// 인영이 카드들
			Set<Integer> set = new HashSet<>(Arrays.asList(cards));
			set.removeAll(Arrays.asList(kyu));
			inYoung = set.toArray(new Integer[0]);
			
			// 인영이 카드 순서 9!
			perm(0);
			
			// 결과 출력
			System.out.printf("#%d %d %d%n", t, win, lose);
		}
	}
	
	// 팩토리얼
	static void perm(int cnt) {
		if(cnt == 9) {
			// 점수 계산
			int kScore = 0;
			int iScore = 0;
			for(int i=0; i<9; i++) {
				if(kyu[i] > nums[i]) kScore += (kyu[i] + nums[i]);
				else iScore += (kyu[i] + nums[i]);
			}
			// 승패 표시
			if(kScore > iScore) win++;
			else lose++;
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(isSelected[i]) continue;
			nums[cnt] = inYoung[i];
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

}
