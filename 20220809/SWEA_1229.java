package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1229 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			List<String> list = new LinkedList<>();

			// 원본 암호문 입력받기
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				list.add(st.nextToken());
			}

			// 명령어 입력받기
			int m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			// 명령어를 쪼개서 처리함
			while (st.hasMoreTokens()) {
				String option = st.nextToken(); // I or D
				int x = Integer.parseInt(st.nextToken()); // 위치
				int y = Integer.parseInt(st.nextToken()); // 개수
				if (option.equals("I")) {
					// x위치에 y개 삽입
					for (int i = 0; i < y; i++) {
						list.add(x + i, st.nextToken());
					}
				} else if (option.equals("D")) {
					// x위치에 y개 삭제
					for (int i = 0; i < y; i++) {
						list.remove(x);
					}
				}
			}

			// 결과 출력
			System.out.printf("#%d ", t);
			for (int i = 0; i < 10; i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();

		}
	}

}
