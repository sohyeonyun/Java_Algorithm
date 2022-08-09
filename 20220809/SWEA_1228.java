package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1228 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			// 연결 리스트 사용
			List<String> list = new LinkedList<>();

			// 원본 암호문 입력 받기
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				list.add(st.nextToken());
			}

			// 명령어 한 줄로 입력 받기
			int m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			// 명령어를 잘라서 각각 처리한다.
			String s;
			while(st.hasMoreTokens()) {
				s = st.nextToken();
				if(s.equals("I")) {
					// 앞에서부터 x 위치 바로 다음에 y 개의 숫자를 삽입한다.
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					// y개의 덧붙일 숫자들
					for(int i=0; i<y; i++) {
						list.add(x + i, st.nextToken());
					}
				}
			}
			
			// 결과 출력
			System.out.printf("#%d ", t);
			for(int i=0; i<10; i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}

	}

}
