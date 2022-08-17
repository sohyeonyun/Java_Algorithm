import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * 회의실 배정
 */

public class BOJ_1931 {
	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;

		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			return end != o.end ? end - o.end : start - o.start;
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Meeting[] maps = new Meeting[n];
		for (int i = 0; i < n; i++) {
			maps[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}

		Arrays.sort(maps); // 빨리 끝나는 순으로 정렬한다.
		
		List<Meeting> list = new ArrayList<>();
		list.add(maps[0]); // 처음 끝나는 회의를 선택하는게 가장 이득이다.

		for (int i = 1; i < maps.length; i++) { // 0번부터 아님!
			// 선택된 회의가 끝나는 시간 후에 시작하는 회의를 선택한다.
			if (list.get(list.size() - 1).end <= maps[i].start) {
				list.add(maps[i]);
			}
		}
		System.out.println(list.size());

	}

}
