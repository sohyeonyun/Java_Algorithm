import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class _1 {

	public static void main(String[] args) {
		String today = "2022.05.19";
		String[] terms = { "A 6", "B 12", "C 3" };
		String[] privacies = { "2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C" };

		String[] tmp = today.split("\\.");
		Date todate = new Date(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));

		HashMap<String, Integer> map = new HashMap<>();
		for (String term : terms) {
			tmp = term.split(" ");
			map.put(tmp[0], Integer.parseInt(tmp[1]));
		}

		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 0; i < privacies.length; i++) {
			String[] input = privacies[i].split(" ");

			tmp = input[0].split("\\.");
			Date date = new Date(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));

			String key = input[1];

			date.m += map.get(key);
			if (date.m > 12) {
				date.y += date.m / 12;
				date.m %= 12;
			}
			
			System.out.println(date);

			if (todate.y < date.y ||
					(todate.y == date.y && todate.m < date.m) ||
					(todate.y == date.y && todate.m == date.m && todate.d < date.d)) {
			} else {
				ans.add(i + 1);
			}

		}
		
		int[] answer = new int[ans.size()];
		for(int i=0; i<ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		System.out.println(Arrays.toString(answer));

	}

	static class Date {
		int y, m, d;

		public Date(int y, int m, int d) {
			this.y = y;
			this.m = m;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Date [y=" + y + ", m=" + m + ", d=" + d + "]";
		}
	}

}
