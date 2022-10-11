import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 회전 초밥 
 */
public class BOJ_15961 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 회전초밥 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가지수 3000
		int k = Integer.parseInt(st.nextToken()); // 연속 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		int[] menu = new int[d + 1];
		// 초깃값 0 ~ k-1 까지 초밥 
		int type = 0;
		int start = 0; // 투 포인터 
		int end = k;
		for (int i = 0; i < k; i++) {
			// 처음 먹는거면 종류 + 1
			if (menu[sushi[i]] == 0) {
				type++;
			}
			menu[sushi[i]]++;
		}

		// 최댓값은 맨 처음 초밥들로
		int MAX = type;
		// 끝까지 돌면서 확인
		for (int i = 0; i < N; i++) {
			
			// end 새로운 초밥 추가
			if (menu[sushi[end]] == 0) {
				type++; // 처음 먹는거면 종류 + 1
			}
			menu[sushi[end]]++;
			// end + 1
			end = (end + 1) % N;
			
			// start 이전 탐색 초밥 삭제 
			menu[sushi[start]]--;
			if (menu[sushi[start]] == 0) {
				type--; // 안먹으면 종류 - 1
			}
			// start + 1
			start = (start + 1) % N;
			
			// 쿠폰 번호 아직 안먹었으면 종류 + 1
			MAX = Math.max(MAX, menu[c] == 0 ? type + 1 : type);
		}
		
		System.out.println(MAX);
	}

}
