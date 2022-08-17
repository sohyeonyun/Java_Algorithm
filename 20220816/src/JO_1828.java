import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
 * 냉장고
 */
public class JO_1828 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int [][] arr = new int[n][2];
		
		// 최저온도, 최고온도
		for(int i=0; i<n; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		// 최고온도를 오름차순 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		// 첫 번째 냉장고의 최대온도를 첫 번째 재료의 최대온도로 정한다.
		int max = arr[0][1];
		int cnt = 1; // 냉장고 개수
		
		// 그 다음 물질부터 검사하면서
		for(int i=1; i<n; i++) {
			// 냉장고 최대온도보다 높은 최저온도의 물질 만나면
			if(max < arr[i][0]) {
				max = arr[i][1];
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
