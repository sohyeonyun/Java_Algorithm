import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomTest {
	static class Meeting implements Comparable<Meeting> {
		int start, end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) { // 1차 종료시간, 2차 시작시간 순으로 오름차순
			return end != o.end ? end - o.end : start - o.start;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Meeting[] maps = new Meeting[N];
		for (int i = 0; i < N; i++) {
			maps[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		List<Meeting> list = getSchedule(maps);
		System.out.println("선택된 회의 갯수 : " + list.size());
		for (Meeting m : list) {
			System.out.println(m.start + " : " + m.end);
		}
	}

	private static List<Meeting> getSchedule(Meeting[] maps) {
		List<Meeting> result = new ArrayList<Meeting>();
		Arrays.sort(maps); // 사전처리 작업 (정렬)
		result.add(maps[0]); // 첫번째 회의는 무조건 선택됨

		for (int i = 1; i < maps.length; i++) { // 두번째 회의부터 마지막 회의까지 검색
			// 선택된 회의의 마지막 시간보다 새롭게 선택하고자 하는 회의의 시작시간이 느린것을 선택함
			if (result.get(result.size() - 1).end <= maps[i].start) {
				result.add(maps[i]);
			}
		}
		return result;
	}

}